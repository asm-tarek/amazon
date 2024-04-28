package com.wsd.amazon.service.impl;

import com.wsd.amazon.domain.request.ProductReq;
import com.wsd.amazon.domain.request.PurchaseReq;
import com.wsd.amazon.domain.request.WishListReq;
import com.wsd.amazon.entity.Product;
import com.wsd.amazon.entity.PurchaseHistory;
import com.wsd.amazon.entity.WishList;
import com.wsd.amazon.exception.BaseException;
import com.wsd.amazon.repository.ProductRepository;
import com.wsd.amazon.repository.PurchaseHistoryRepository;
import com.wsd.amazon.repository.WishListRepository;
import com.wsd.amazon.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final WishListRepository wishListRepository;

    public ProductServiceImpl(ProductRepository productRepository, PurchaseHistoryRepository purchaseHistoryRepository, WishListRepository wishListRepository) {
        this.productRepository = productRepository;
        this.purchaseHistoryRepository = purchaseHistoryRepository;
        this.wishListRepository = wishListRepository;
    }

    @Override
    public Product createProduct(ProductReq productReq) {
        Product product = new Product();
        BeanUtils.copyProperties(productReq, product);

        log.info("Creating product....");
        return productRepository.save(product);
    }

    @Override
    public PurchaseHistory purchase(PurchaseReq purchaseReq) {
        Optional<Product> product = productRepository.findById(purchaseReq.getProductId());
        if (product.isEmpty()) throw new BaseException("Invalid Product!");

        log.info("Saving purchase data to history table....");

        return purchaseHistoryRepository.save(PurchaseHistory.builder()
                .product(product.get())
                .userId(purchaseReq.getUserId())
                .purchaseDate(new Date())
                .count(purchaseReq.getCount())
                .unitPrice(purchaseReq.getUnitPrice())
                .totalAmount(purchaseReq.getUnitPrice().multiply(BigDecimal.valueOf(purchaseReq.getCount())))
                .build()
        );
    }

    @Override
    public WishList addToWishList(WishListReq wishListReq) {
        Optional<Product> product = productRepository.findById(wishListReq.getProductId());
        if (product.isEmpty()) throw new BaseException("Invalid Product!");

        log.info("Adding product to user wishList....");

        return wishListRepository.save(WishList.builder()
                .product(product.get())
                .userId(wishListReq.getUserId())
                .addOnDate(new Date())
                .build()
        );
    }

    @Override
    public Page<WishList> getUserWishList(long userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        log.info("Getting user wish list products...");
        return wishListRepository.findByUserId(userId, pageable);
    }

    @Override
    public BigDecimal getCurrentDayTotalSaleAmount() {
        log.info("Getting current day total sale amount from db...");
        return purchaseHistoryRepository.getCurrentDayTotalSaleAmount();
    }

    @Override
    public Date getTopSaleDay(Date fromDate, Date toDate) {
        log.info("Getting top sale day in given time period...");
        return purchaseHistoryRepository.getTopSaleDay(fromDate, toDate);
    }

    @Override
    public List<Long> getAllTimeTop5SellingItem() {
        log.info("Getting all-time top 5 selling items based on total amount sales...");
        return purchaseHistoryRepository.getAllTimeTop5SellingItem();
    }

    @Override
    public List<Long> getLastMonthTop5SellingItem() {
        log.info("Getting last month top 5 selling items based on total product count...");
        return purchaseHistoryRepository.getLastMonthTop5SellingItem();
    }
}

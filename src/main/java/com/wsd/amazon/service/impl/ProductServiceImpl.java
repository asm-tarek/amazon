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
import com.wsd.amazon.utils.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class ProductServiceImpl extends BaseService implements ProductService {
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

        logger.trace("Creating product....");
        return productRepository.save(product);
    }

    @Override
    public PurchaseHistory purchase(PurchaseReq purchaseReq) {
        Optional<Product> product = productRepository.findById(purchaseReq.getProductId());
        if (product.isEmpty()) throw new BaseException("Invalid Product!");

        logger.trace("Saving purchase data to history table....");

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

        logger.trace("Adding product to user wishList....");

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

        logger.trace("Getting user wish list products...");
        return wishListRepository.findByUserId(userId, pageable);
    }
}

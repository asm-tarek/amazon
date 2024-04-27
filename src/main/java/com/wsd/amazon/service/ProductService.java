package com.wsd.amazon.service;

import com.wsd.amazon.domain.request.ProductReq;
import com.wsd.amazon.domain.request.PurchaseReq;
import com.wsd.amazon.domain.request.WishListReq;
import com.wsd.amazon.entity.Product;
import com.wsd.amazon.entity.PurchaseHistory;
import com.wsd.amazon.entity.WishList;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;

public interface ProductService {
    Product createProduct(ProductReq productReq);

    PurchaseHistory purchase(PurchaseReq purchaseReq);

    WishList addToWishList(WishListReq wishListReq);

    Page<WishList> getUserWishList(long userId, int pageNo, int pageSize);

    BigDecimal getCurrentDayTotalSaleAmount();

    Date getTopSaleDay(Date fromDate, Date toDate);
}

package com.wsd.amazon.service;

import com.wsd.amazon.domain.request.ProductReq;
import com.wsd.amazon.entity.Product;

public interface ProductService {
    Product createProduct(ProductReq productReq);
}

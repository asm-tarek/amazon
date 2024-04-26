package com.wsd.amazon.service.impl;

import com.wsd.amazon.domain.request.ProductReq;
import com.wsd.amazon.entity.Product;
import com.wsd.amazon.repository.ProductRepository;
import com.wsd.amazon.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(ProductReq productReq) {
        Product product = new Product();
        BeanUtils.copyProperties(productReq, product);
        return productRepository.save(product);
    }
}

package com.wsd.amazon.controller;

import com.wsd.amazon.domain.request.ProductReq;
import com.wsd.amazon.entity.Product;
import com.wsd.amazon.service.ProductService;
import com.wsd.amazon.utils.ApiResponse;
import com.wsd.amazon.utils.ResponseUtils;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wsd.amazon.utils.Constants.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ApiResponse<Product> createProduct(@Valid @RequestBody ProductReq productReq) {
        return ResponseUtils.buildSuccessResponse(productService.createProduct(productReq));
    }
}

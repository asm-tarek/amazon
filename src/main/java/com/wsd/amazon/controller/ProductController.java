package com.wsd.amazon.controller;

import com.wsd.amazon.domain.request.ProductReq;
import com.wsd.amazon.domain.request.PurchaseReq;
import com.wsd.amazon.domain.request.WishListReq;
import com.wsd.amazon.entity.Product;
import com.wsd.amazon.entity.PurchaseHistory;
import com.wsd.amazon.entity.WishList;
import com.wsd.amazon.service.ProductService;
import com.wsd.amazon.utils.ApiResponse;
import com.wsd.amazon.utils.ResponseUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/purchase")
    public ApiResponse<PurchaseHistory> purchase(@Valid @RequestBody PurchaseReq purchaseReq) {
        return ResponseUtils.buildSuccessResponse(productService.purchase(purchaseReq));
    }

    @PostMapping("/wishlist")
    public ApiResponse<WishList> addToWishList(@Valid @RequestBody WishListReq wishListReq) {
        return ResponseUtils.buildSuccessResponse(productService.addToWishList(wishListReq));
    }

    @GetMapping("/wishlist")
    public ApiResponse<Page<WishList>> getUserWishList(@RequestParam long userId,
                                                       @RequestParam(defaultValue = "0") int pageNo,
                                                       @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseUtils.buildSuccessResponse(productService.getUserWishList(userId, pageNo, pageSize));
    }
}
package com.wsd.amazon.controller;

import com.wsd.amazon.domain.request.ProductReq;
import com.wsd.amazon.domain.request.PurchaseReq;
import com.wsd.amazon.domain.request.WishListReq;
import com.wsd.amazon.entity.Product;
import com.wsd.amazon.entity.PurchaseHistory;
import com.wsd.amazon.entity.WishList;
import com.wsd.amazon.service.ProductService;
import com.wsd.amazon.utils.ApiResponse;
import com.wsd.amazon.utils.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
        return ResponseUtil.buildSuccessResponse(productService.createProduct(productReq));
    }

    @PostMapping("/purchase")
    public ApiResponse<PurchaseHistory> purchase(@Valid @RequestBody PurchaseReq purchaseReq) {
        return ResponseUtil.buildSuccessResponse(productService.purchase(purchaseReq));
    }

    @PostMapping("/wishlist")
    public ApiResponse<WishList> addToWishList(@Valid @RequestBody WishListReq wishListReq) {
        return ResponseUtil.buildSuccessResponse(productService.addToWishList(wishListReq));
    }

    @GetMapping("/wishlist")
    public ApiResponse<Page<WishList>> getUserWishList(@RequestParam long userId,
                                                       @RequestParam(defaultValue = "0") int pageNo,
                                                       @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseUtil.buildSuccessResponse(productService.getUserWishList(userId, pageNo, pageSize));
    }

    @GetMapping("/today/total-sale")
    public ApiResponse<BigDecimal> getCurrentDayTotalSaleAmount() {
        return ResponseUtil.buildSuccessResponse(productService.getCurrentDayTotalSaleAmount());
    }

    @GetMapping("/top-sale-day")
    public ApiResponse<Date> getTopSaleDay(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        return ResponseUtil.buildSuccessResponse(productService.getTopSaleDay(fromDate, toDate));
    }

    @GetMapping("/all-time/top5")
    public ApiResponse<List<Long>> getTopSaleDay() {
        return ResponseUtil.buildSuccessResponse(productService.getAllTimeTop5SellingItem());
    }

    @GetMapping("/last-month/top5")
    public ApiResponse<List<Long>> getLastMonthTop5SellingItem() {
        return ResponseUtil.buildSuccessResponse(productService.getLastMonthTop5SellingItem());
    }
}
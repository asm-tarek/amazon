package com.wsd.amazon.domain.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseReq {
    @NotNull(message = "User Id should not be null!")
    private Long userId;
    @NotNull(message = "Product Id should not be null!")
    private Long productId;
    @Min(value = 1, message = "Count should be greater than 0!")
    private int count;
    @DecimalMin(value = "0.0", inclusive = false, message = "Unit Price should be greater than 0.0!")
    private BigDecimal unitPrice;
}

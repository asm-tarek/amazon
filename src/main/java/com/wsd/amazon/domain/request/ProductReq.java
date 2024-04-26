package com.wsd.amazon.domain.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductReq {
    @NotBlank(message = "Title should not be null!")
    private String title;
    @NotBlank(message = "Type should not be null!")
    private String type;
    @NotBlank(message = "Description should not be null!")
    private String description;
    @DecimalMin(value = "0.0", inclusive = false, message = "Weight should be greater than 0.0!")
    private double weight;
    @DecimalMin(value = "0.0", inclusive = false, message = "Unit Price should be greater than 0.0!")
    private BigDecimal unitPrice;
}

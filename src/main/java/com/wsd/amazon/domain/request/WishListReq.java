package com.wsd.amazon.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WishListReq {
    @NotNull(message = "User Id should not be null!")
    private Long userId;
    @NotNull(message = "Product Id should not be null!")
    private Long productId;
}

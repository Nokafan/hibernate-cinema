package com.dev.cinema.dto.shoppingcart;

import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class ShoppingCartRequestDto {
    @Positive(message = "User id should be positive")
    private Long userId;
}

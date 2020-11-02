package com.dev.cinema.dto.shoppingcart;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShoppingCartRequestDto {
    @NotNull(message = "User id shouldn't be null")
    @Min(value = 0, message = "User id should be >= 0")
    private Long userId;
}

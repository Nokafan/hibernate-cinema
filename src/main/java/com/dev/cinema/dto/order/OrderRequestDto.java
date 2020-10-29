package com.dev.cinema.dto.order;

import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderRequestDto {
    @Positive(message = "User id should be positive")
    private Long userId;
}

package com.dev.cinema.dto.order;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDto {
    @NotNull(message = "User id can't be null")
    @Min(value = 0, message = "User id should be >= 0")
    private Long userId;
}

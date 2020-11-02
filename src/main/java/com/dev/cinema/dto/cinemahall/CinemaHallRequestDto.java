package com.dev.cinema.dto.cinemahall;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CinemaHallRequestDto {
    @NotNull(message = "Capasity can't be null")
    @Min(value = 0, message = "Capasity should be > 0")
    private Long capacity;
    @NotNull
    private String description;
}

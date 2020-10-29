package com.dev.cinema.dto.cinemahall;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class CinemaHallRequestDto {
    @Positive
    private Long capacity;
    @NotNull
    private String description;
}

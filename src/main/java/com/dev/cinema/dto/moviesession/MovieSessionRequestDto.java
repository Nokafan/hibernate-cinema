package com.dev.cinema.dto.moviesession;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieSessionRequestDto {
    @NotNull(message = "Movie id can't be null")
    @Min(value = 0, message = "Movie id should be >= 0")
    private Long movieId;
    @NotNull(message = "Cinema hall  id can't be null")
    @Min(value = 0, message = "Cinema hall  id should be >= 0")
    private Long cinemaHallId;
    @FutureOrPresent(message = "Session time couldn't be at the past")
    private String sessionTime;
}

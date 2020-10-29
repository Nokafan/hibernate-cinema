package com.dev.cinema.dto.moviesession;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class MovieSessionRequestDto {
    @Positive(message = "Movie id should be positive")
    private Long movieId;
    @Positive(message = "Cinema hall id should be positive")
    private Long cinemaHallId;
    @FutureOrPresent(message = "Session time couldn't be at the past")
    private String sessionTime;
}

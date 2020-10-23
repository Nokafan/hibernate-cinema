package com.dev.cinema.dto.moviesession;

import lombok.Data;

@Data
public class MovieSessionRequestDto {
    private Long movieId;
    private Long cinemaHallId;
    private String sessionTime;
}

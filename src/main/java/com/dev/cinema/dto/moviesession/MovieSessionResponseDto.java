package com.dev.cinema.dto.moviesession;

import lombok.Data;

@Data
public class MovieSessionResponseDto {
    private Long id;
    private Long movieId;
    private String movieTittle;
    private Long cinemaHallId;
    private String dateTime;
}

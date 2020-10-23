package com.dev.cinema.dto.moviesession;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MovieSessionResponseDto {
    private Long id;
    private Movie movie;
    private CinemaHall cinemaHall;
    private LocalDateTime time;
}

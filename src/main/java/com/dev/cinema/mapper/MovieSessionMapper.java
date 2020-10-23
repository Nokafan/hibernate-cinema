package com.dev.cinema.mapper;

import com.dev.cinema.dto.moviesession.MovieSessionRequestDto;
import com.dev.cinema.dto.moviesession.MovieSessionResponceDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService hallService;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    @Autowired
    public MovieSessionMapper(MovieService movieService, CinemaHallService hallService) {
        this.movieService = movieService;
        this.hallService = hallService;
    }

    public MovieSession toMovieSessionFromDto(MovieSessionRequestDto requestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.get(requestDto.getMovieId()));
        movieSession.setCinemaHall(hallService.get(requestDto.getCinemaHallId()));
        movieSession.setTime(LocalDateTime.parse(requestDto.getSessionTime(), formatter));
        return movieSession;
    }

    public MovieSessionResponceDto fromMovieSessionToDto(MovieSession movieSession) {
        MovieSessionResponceDto sessionDto = new MovieSessionResponceDto();
        sessionDto.setId(movieSession.getId());
        sessionDto.setMovie(movieSession.getMovie());
        sessionDto.setCinemaHall(movieSession.getCinemaHall());
        sessionDto.setTime(movieSession.getTime());
        return sessionDto;
    }
}

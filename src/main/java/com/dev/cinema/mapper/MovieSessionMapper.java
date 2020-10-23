package com.dev.cinema.mapper;

import com.dev.cinema.dto.moviesession.MovieSessionRequestDto;
import com.dev.cinema.dto.moviesession.MovieSessionResponseDto;
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

    public MovieSessionResponseDto fromMovieSessionToDto(MovieSession movieSession) {
        MovieSessionResponseDto sessionDto = new MovieSessionResponseDto();
        sessionDto.setId(movieSession.getId());
        sessionDto.setMovieId(movieSession.getMovie().getId());
        sessionDto.setMovieTittle(movieSession.getMovie().getTitle());
        sessionDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        sessionDto.setDateTime(movieSession.getTime().toString());
        return sessionDto;
    }
}

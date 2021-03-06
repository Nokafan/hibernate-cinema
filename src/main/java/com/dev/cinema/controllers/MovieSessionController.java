package com.dev.cinema.controllers;

import com.dev.cinema.dto.moviesession.MovieSessionRequestDto;
import com.dev.cinema.dto.moviesession.MovieSessionResponseDto;
import com.dev.cinema.mapper.MovieSessionMapper;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionMapper mapper;
    private final MovieSessionService movieSessionService;

    @Autowired
    public MovieSessionController(MovieSessionMapper mapper,
                                  MovieSessionService movieSessionService) {
        this.mapper = mapper;
        this.movieSessionService = movieSessionService;
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailableMovieSessions(
            @RequestParam @Valid Long movieId,
            @RequestParam @Valid @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date)
                .stream()
                .map(mapper::fromMovieSessionToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addMovieSession(@RequestBody MovieSessionRequestDto requestDto) {
        movieSessionService.add(mapper.toMovieSessionFromDto(requestDto));
    }
}

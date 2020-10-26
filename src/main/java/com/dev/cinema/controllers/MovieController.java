package com.dev.cinema.controllers;

import com.dev.cinema.dto.movie.MovieRequestDto;
import com.dev.cinema.dto.movie.MovieResponseDto;
import com.dev.cinema.mapper.MovieMapper;
import com.dev.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieMapper mapper;
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieMapper mapper, MovieService movieService) {
        this.mapper = mapper;
        this.movieService = movieService;
    }

    @PostMapping
    public void addMovie(@RequestBody MovieRequestDto movieDto) {
        movieService.add(mapper.movieDtoToMovie(movieDto));
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(mapper::movieToMovieResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MovieResponseDto getMovie(@PathVariable Long id) {
        return mapper.movieToMovieResponseDto(movieService.get(id));
    }
}

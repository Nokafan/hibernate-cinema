package com.dev.cinema.mapper;

import com.dev.cinema.dto.movie.MovieRequestDto;
import com.dev.cinema.dto.movie.MovieResponceDto;
import com.dev.cinema.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieResponceDto movieToMovieDto(Movie movie) {
        MovieResponceDto movieDto = new MovieResponceDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        return movieDto;
    }

    public Movie movieDtoToMovie(MovieRequestDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        return movie;
    }
}

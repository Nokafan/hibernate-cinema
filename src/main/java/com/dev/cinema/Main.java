package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movieOne = new Movie();
        movieOne.setTitle("Fast and Furious - 1");
        movieOne.setDescription("Cool movie");
        movieService.add(movieOne);

        Movie movieTwo = new Movie();
        movieTwo.setTitle("Fast and Furious - 2");
        movieTwo.setDescription("Cool movie");

        movieService.add(movieTwo);

        Movie movieThree = new Movie();
        movieThree.setTitle("Fast and Furious - 3");
        movieThree.setDescription("Cool movie");
        movieService.add(movieThree);

        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService
                = (CinemaHallService) injector.getInstance(CinemaHallService.class);

        CinemaHall cinemaHallOne = new CinemaHall();
        cinemaHallOne.setDescription("Red ONE");
        cinemaHallOne.setCapacity(77L);
        cinemaHallService.add(cinemaHallOne);

        CinemaHall cinemaHallTwo = new CinemaHall();
        cinemaHallTwo.setDescription("Blue TWO");
        cinemaHallTwo.setCapacity(232L);
        cinemaHallService.add(cinemaHallTwo);

        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSessionFirst = new MovieSession();
        movieSessionFirst.setCinemaHall(cinemaHallOne);
        movieSessionFirst.setMovie(movieOne);
        movieSessionFirst.setTime(LocalDateTime.of(2020, 11, 11, 11, 11));

        MovieSession movieSessionTwo = new MovieSession();
        movieSessionTwo.setCinemaHall(cinemaHallTwo);
        movieSessionTwo.setMovie(movieTwo);
        movieSessionTwo.setTime(LocalDateTime.of(2020, 11, 11, 22, 22));

        MovieSession movieSessionThree = new MovieSession();
        movieSessionThree.setCinemaHall(cinemaHallTwo);
        movieSessionThree.setMovie(movieTwo);
        movieSessionThree.setTime(LocalDateTime.of(20, 11, 12, 22, 22));

        MovieSessionService movieSessionService
                = (MovieSessionService) injector.getInstance(MovieSessionService.class);

        movieSessionService.add(movieSessionFirst);
        movieSessionService.add(movieSessionTwo);
        movieSessionService.add(movieSessionThree);

        movieSessionService.findAvailableSessions(1L, LocalDate.of(2020, 11, 11))
                .forEach(System.out::println);

        movieSessionService.findAvailableSessions(2L, LocalDate.of(2020, 10, 11))
                .forEach(System.out::println);

        movieService.getAll().forEach(System.out::println);
    }
}

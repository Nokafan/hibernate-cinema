package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

@Log4j
public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        MovieService movieService = context.getBean(MovieService.class);
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

        log.info("Get all movies " + movieService.getAll());

        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);

        CinemaHall cinemaHallOne = new CinemaHall();
        cinemaHallOne.setDescription("Red ONE");
        cinemaHallOne.setCapacity(77L);
        cinemaHallService.add(cinemaHallOne);

        CinemaHall cinemaHallTwo = new CinemaHall();
        cinemaHallTwo.setDescription("Blue TWO");
        cinemaHallTwo.setCapacity(232L);
        cinemaHallService.add(cinemaHallTwo);

        log.info("Get all cinemaHallService " + cinemaHallService.getAll());

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
                = context.getBean(MovieSessionService.class);

        movieSessionService.add(movieSessionFirst);
        movieSessionService.add(movieSessionTwo);
        movieSessionService.add(movieSessionThree);

        log.info("Find availible session " 
                + movieSessionService.findAvailableSessions(1L, LocalDate.of(2020, 11, 11)));
        log.info("Find availible session " 
                + movieSessionService.findAvailableSessions(2L, LocalDate.of(2020, 10, 11)));

        log.info("Get all movies " + movieService.getAll());

        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        User userOne = authenticationService.register("userOne@ukr.net", "1234");
        User userTwo = authenticationService.register("userTwo@ukr.net", "1234");

        ShoppingCartService cartService = context.getBean(ShoppingCartService.class);
        cartService.addSession(movieSessionFirst, userOne);
        cartService.addSession(movieSessionFirst, userOne);
        cartService.addSession(movieSessionTwo, userOne);
        cartService.addSession(movieSessionThree, userTwo);
        cartService.addSession(movieSessionFirst, userTwo);

        log.info(cartService.getByUser(userOne));
        log.info(cartService.getByUser(userTwo));

        OrderService orderService = context.getBean(OrderService.class);
        List<Ticket> arrayListTicketsUserOne =
                new ArrayList<>(cartService.getByUser(userOne).getTickets());
        List<Ticket> arrayListTicketsUserTwo =
                new ArrayList<>(cartService.getByUser(userTwo).getTickets());
        orderService.completeOrder(arrayListTicketsUserOne, userOne);
        orderService.completeOrder(arrayListTicketsUserTwo, userTwo);
        cartService.addSession(movieSessionFirst, userOne);
        cartService.addSession(movieSessionFirst, userOne);
        cartService.addSession(movieSessionFirst, userOne);
        cartService.addSession(movieSessionFirst, userTwo);
        cartService.addSession(movieSessionFirst, userTwo);
        cartService.addSession(movieSessionTwo, userOne);
        cartService.addSession(movieSessionTwo, userOne);

        log.info("Get order history user One " + orderService.getOrderHistory(userOne));
        log.info("Get order history for user Two " + orderService.getOrderHistory(userTwo));
    }
}

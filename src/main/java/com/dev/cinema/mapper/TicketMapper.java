package com.dev.cinema.mapper;

import com.dev.cinema.dto.ticket.TicketRequestDto;
import com.dev.cinema.dto.ticket.TicketResponseDto;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    private final UserService userService;
    private final MovieSessionService movieSessionService;

    @Autowired
    public TicketMapper(UserService userService, MovieSessionService movieSessionService) {
        this.userService = userService;
        this.movieSessionService = movieSessionService;
    }

    public TicketResponseDto fromTicketToResponseDto(Ticket ticket) {
        TicketResponseDto responceDto = new TicketResponseDto();
        responceDto.setUserId(ticket.getUser().getId());
        responceDto.setTicketId(ticket.getId());
        responceDto.setMovieSessionId(ticket.getMovieSession().getId());
        return responceDto;
    }

    public Ticket fromDtoToTicket(TicketRequestDto requestDto) {
        Ticket ticket = new Ticket();
        ticket.setUser(userService.get(requestDto.getUserId()));
        ticket.setMovieSession(movieSessionService.get(requestDto.getMovieSessionId()));
        return ticket;
    }
}

package com.dev.cinema.mapper;

import com.dev.cinema.dto.ticket.TicketResponseDto;
import com.dev.cinema.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketResponseDto fromTicketToResponseDto(Ticket ticket) {
        TicketResponseDto responceDto = new TicketResponseDto();
        responceDto.setUserId(ticket.getUser().getId());
        responceDto.setTicketId(ticket.getId());
        responceDto.setMovieSessionId(ticket.getMovieSession().getId());
        return responceDto;
    }
}

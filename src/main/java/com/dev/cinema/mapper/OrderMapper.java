package com.dev.cinema.mapper;

import com.dev.cinema.dto.order.OrderResponseDto;
import com.dev.cinema.dto.ticket.TicketResponseDto;
import com.dev.cinema.model.Order;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;

    @Autowired
    public OrderMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public OrderResponseDto fromOrderToResponseDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setUserId(order.getUser().getId());
        responseDto.setOrderDateTime(order.getOrderDate().toString());
        List<TicketResponseDto> listTicketDto = order.getTickets().stream()
                .map(ticketMapper::fromTicketToResponseDto)
                .collect(Collectors.toList());
        responseDto.setTickets(listTicketDto);
        return responseDto;
    }
}

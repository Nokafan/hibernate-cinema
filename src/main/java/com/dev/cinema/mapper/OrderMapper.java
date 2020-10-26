package com.dev.cinema.mapper;

import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.dto.order.OrderRequestDto;
import com.dev.cinema.dto.order.OrderResponseDto;
import com.dev.cinema.dto.ticket.TicketResponseDto;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;
    private final UserService userService;

    @Autowired
    public OrderMapper(TicketDao ticketDao, TicketMapper ticketMapper, UserService userService) {
        this.ticketMapper = ticketMapper;
        this.userService = userService;
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

    public Order fromDtoToOrder(OrderRequestDto requestDto) {
        Order order = new Order();
        order.setUser(userService.get(requestDto.getUserId()));
        List<Ticket> ticketList = requestDto.getTickets().stream()
                .map(ticketMapper::fromDtoToTicket)
                .collect(Collectors.toList());
        order.setTickets(ticketList);
        return order;
    }
}

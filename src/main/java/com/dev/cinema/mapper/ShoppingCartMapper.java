package com.dev.cinema.mapper;

import com.dev.cinema.dto.shoppingcart.ShoppingCartResponceDto;
import com.dev.cinema.dto.ticket.TicketResponseDto;
import com.dev.cinema.model.ShoppingCart;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    @Autowired
    public ShoppingCartMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponceDto fromShoppingCartToResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponceDto responceDto = new ShoppingCartResponceDto();
        responceDto.setUserId(shoppingCart.getId());
        List<TicketResponseDto> ticketsListDto = shoppingCart.getTickets()
                .stream()
                .map(ticketMapper::fromTicketToResponseDto)
                .collect(Collectors.toList());
        responceDto.setTickets(ticketsListDto);
        return responceDto;
    }
}

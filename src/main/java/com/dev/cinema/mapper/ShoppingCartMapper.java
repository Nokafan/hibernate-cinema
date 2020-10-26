package com.dev.cinema.mapper;

import com.dev.cinema.dto.shoppingcart.ShoppingCartRequestDto;
import com.dev.cinema.dto.shoppingcart.ShoppingCartResponceDto;
import com.dev.cinema.dto.ticket.TicketResponseDto;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final ShoppingCartService shoppingCartService;
    private final TicketMapper ticketMapper;
    private final UserService userService;

    @Autowired
    public ShoppingCartMapper(ShoppingCartService shoppingCartService,
                              TicketMapper ticketMapper,
                              UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.ticketMapper = ticketMapper;
        this.userService = userService;
    }

    public ShoppingCartResponceDto fromShoppingCartToResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponceDto responceDto = new ShoppingCartResponceDto();
        responceDto.setUserId(shoppingCart.getId());
        List<TicketResponseDto> ticketsListDto = shoppingCartService.get(shoppingCart.getId())
                .getTickets()
                .stream()
                .map(ticketMapper::fromTicketToResponseDto)
                .collect(Collectors.toList());
        responceDto.setTickets(ticketsListDto);
        return responceDto;
    }

    public ShoppingCart fromDtoToShoppingCart(ShoppingCartRequestDto requestDto) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(userService.get(requestDto.getUserId()));
        shoppingCart.setTickets(shoppingCartService.get(requestDto.getUserId()).getTickets());
        return shoppingCart;
    }
}

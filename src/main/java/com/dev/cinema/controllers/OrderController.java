package com.dev.cinema.controllers;

import com.dev.cinema.dto.order.OrderRequestDto;
import com.dev.cinema.dto.order.OrderResponseDto;
import com.dev.cinema.mapper.OrderMapper;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService,
                           ShoppingCartService shoppingCartService,
                           UserService userService,
                           OrderMapper orderMapper) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public void addOrder(@RequestBody OrderRequestDto requestDto) {
        orderService.completeOrder(
                shoppingCartService.get(requestDto.getUserId()).getTickets(),
                userService.get(requestDto.getUserId()));
    }

    @GetMapping
    public List<OrderResponseDto> getAll(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.get(userId))
                .stream()
                .map(orderMapper::fromOrderToResponseDto)
                .collect(Collectors.toList());
    }
}

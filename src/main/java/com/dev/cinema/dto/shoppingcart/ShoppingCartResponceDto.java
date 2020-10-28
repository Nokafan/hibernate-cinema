package com.dev.cinema.dto.shoppingcart;

import com.dev.cinema.dto.ticket.TicketResponseDto;
import java.util.List;
import lombok.Data;

@Data
public class ShoppingCartResponceDto {
    private Long userId;
    private List<TicketResponseDto> tickets;
}

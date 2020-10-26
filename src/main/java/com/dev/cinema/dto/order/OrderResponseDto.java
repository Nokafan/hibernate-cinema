package com.dev.cinema.dto.order;

import com.dev.cinema.dto.ticket.TicketResponseDto;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long userId;
    private String orderDateTime;
    private List<TicketResponseDto> tickets;
}

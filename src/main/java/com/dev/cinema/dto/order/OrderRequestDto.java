package com.dev.cinema.dto.order;

import com.dev.cinema.dto.ticket.TicketRequestDto;
import java.util.List;
import lombok.Data;

@Data
public class OrderRequestDto {
    private Long userId;
    private List<TicketRequestDto> tickets;
}

package com.dev.cinema.dto.ticket;

import lombok.Data;

@Data
public class TicketResponseDto {
    private Long ticketId;
    private Long movieSessionId;
    private Long userId;
}

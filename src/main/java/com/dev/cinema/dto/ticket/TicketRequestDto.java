package com.dev.cinema.dto.ticket;

import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class TicketRequestDto {
    @Positive(message = "Movie session id should be positive")
    private Long movieSessionId;
    @Positive(message = "User id should be positive")
    private Long userId;
}

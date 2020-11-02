package com.dev.cinema.dto.ticket;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketRequestDto {
    @NotNull(message = "Movie session id can't be null")
    @Min(value = 0, message = "Movie session id should be >= 0")
    private Long movieSessionId;
    @NotNull(message = "User id can't be null")
    @Min(value = 0, message = "User id should be >= 0")
    private Long userId;
}

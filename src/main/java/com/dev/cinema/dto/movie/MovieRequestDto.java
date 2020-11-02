package com.dev.cinema.dto.movie;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieRequestDto {
    @NotBlank(message = "Title couldn't be blank")
    private String title;
    @NotNull(message = "Description couldn't be null")
    private String description;
}

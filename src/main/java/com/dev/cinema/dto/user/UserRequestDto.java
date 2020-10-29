package com.dev.cinema.dto.user;

import javax.validation.constraints.Email;
import lombok.Data;

@Data
public class UserRequestDto {
    @Email(message = "Email should be valid")
    private String email;
    private String password;
}

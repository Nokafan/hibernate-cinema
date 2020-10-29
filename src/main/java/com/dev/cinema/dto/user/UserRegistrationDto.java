package com.dev.cinema.dto.user;

import com.dev.cinema.security.validator.EmailValidatorConstraint;
import com.dev.cinema.security.validator.PasswordValidatorConstraint;
import lombok.Data;

@Data
@PasswordValidatorConstraint
public class UserRegistrationDto {
    @EmailValidatorConstraint
    private String email;
    private String password;
    private String repeatedPassword;
}

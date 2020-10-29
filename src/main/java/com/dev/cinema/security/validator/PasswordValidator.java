package com.dev.cinema.security.validator;

import com.dev.cinema.dto.user.UserRegistrationDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<PasswordValidatorConstraint, UserRegistrationDto> {
    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    @Override
    public boolean isValid(UserRegistrationDto dto, ConstraintValidatorContext context) {
        String field = dto.getPassword();
        String fieldMatch = dto.getRepeatedPassword();
        return field != null && field.matches(PASSWORD_PATTERN)
                && field.equals(fieldMatch);
    }
}

package com.dev.cinema.controllers;

import com.dev.cinema.dto.user.UserRegistrationDto;
import com.dev.cinema.security.AuthenticationService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService service;

    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/registration")
    public void registration(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        service.register(userRegistrationDto.getEmail(),
                userRegistrationDto.getPassword());
    }
}

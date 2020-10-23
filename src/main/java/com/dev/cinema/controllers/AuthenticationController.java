package com.dev.cinema.controllers;

import com.dev.cinema.dto.UserDto;
import com.dev.cinema.exeption.AuthenticationException;
import com.dev.cinema.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {
    private final AuthenticationService service;

    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/registration")
    public void registration(@RequestBody UserDto userDto) {
        service.register(userDto.getEmail(), userDto.getPassword());
    }

    @PostMapping("/login")
    public void login(@RequestBody UserDto userDto) throws AuthenticationException {
        service.login(userDto.getEmail(), userDto.getPassword());
    }
}

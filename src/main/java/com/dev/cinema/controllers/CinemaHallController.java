package com.dev.cinema.controllers;

import com.dev.cinema.dto.cinemahall.CinemaHallRequestDto;
import com.dev.cinema.dto.cinemahall.CinemaHallResponceDto;
import com.dev.cinema.mapper.CinemaHallMapper;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService hallService;
    private final CinemaHallMapper cinemaHallMapper;

    @Autowired
    public CinemaHallController(CinemaHallService hallService, CinemaHallMapper cinemaHallMapper) {
        this.hallService = hallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @PostMapping
    public void addCinemaHall(@RequestBody @Valid CinemaHallRequestDto requestDto) {
        hallService.add(cinemaHallMapper.fromRequestDtoToCinemaHall(requestDto));
    }

    @GetMapping
    public List<CinemaHallResponceDto> getAll() {
        return hallService.getAll()
                .stream()
                .map(cinemaHallMapper::fromCinemaHallToResponseDto)
                .collect(Collectors.toList());
    }
}

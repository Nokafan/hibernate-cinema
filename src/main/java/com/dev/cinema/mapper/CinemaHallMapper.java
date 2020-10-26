package com.dev.cinema.mapper;

import com.dev.cinema.dto.cinemahall.CinemaHallRequestDto;
import com.dev.cinema.dto.cinemahall.CinemaHallResponceDto;
import com.dev.cinema.model.CinemaHall;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {
    public CinemaHallResponceDto fromCinemaHallToResponseDto(CinemaHall cinemaHall) {
        CinemaHallResponceDto responceDto = new CinemaHallResponceDto();
        responceDto.setId(cinemaHall.getId());
        responceDto.setCapacity(cinemaHall.getCapacity());
        responceDto.setDescription(cinemaHall.getDescription());
        return responceDto;
    }

    public CinemaHall fromRequestDtoToCinemaHall(CinemaHallRequestDto requestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(requestDto.getCapacity());
        cinemaHall.setDescription(requestDto.getDescription());
        return cinemaHall;
    }
}

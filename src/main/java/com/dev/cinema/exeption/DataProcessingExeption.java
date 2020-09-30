package com.dev.cinema.exeption;

public class DataProcessingExeption extends RuntimeException {
    public DataProcessingExeption(String message, Exception e) {
        super(message, e);
    }
}

package com.example.house.exception;

public class PersonValidationException extends RuntimeException {
    private String message;

    public PersonValidationException() {}

    public PersonValidationException(String message) {
        super(message);
        this.message = message;
    }
}
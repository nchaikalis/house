package com.example.house.exception.person;

public class PersonValidationException extends PersonException {
    private String message;

    public PersonValidationException() {}

    public PersonValidationException(String message) {
        super(message);
        this.message = message;
    }
}
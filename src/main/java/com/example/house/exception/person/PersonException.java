package com.example.house.exception.person;

public class PersonException extends RuntimeException {
    private String message;

    public PersonException() {}

    public PersonException(String message) {
        super(message);
        this.message = message;
    }
}
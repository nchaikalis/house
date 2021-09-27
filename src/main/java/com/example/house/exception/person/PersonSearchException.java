package com.example.house.exception.person;

public class PersonSearchException extends PersonException {
    private String message;

    public PersonSearchException() {}

    public PersonSearchException(String message) {
        super(message);
        this.message = message;
    }
}

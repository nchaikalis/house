package com.example.house.exception;

public class PersonSearchException extends RuntimeException {
    private String message;

    public PersonSearchException() {}

    public PersonSearchException(String message) {
        super(message);
        this.message = message;
    }
}

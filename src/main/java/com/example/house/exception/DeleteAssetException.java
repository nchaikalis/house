package com.example.house.exception;

public class DeleteAssetException extends RuntimeException {
    private String message;

    public DeleteAssetException() {}

    public DeleteAssetException(String message) {
        super(message);
        this.message = message;
    }
}
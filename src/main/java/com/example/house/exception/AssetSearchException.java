package com.example.house.exception;

public class AssetSearchException extends RuntimeException {
    private String message;

    public AssetSearchException() {}

    public AssetSearchException(String message) {
        super(message);
        this.message = message;
    }
}

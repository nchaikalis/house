package com.example.house.exception.asset;

public class AssetException extends RuntimeException {
    private String message;

    public AssetException() {}

    public AssetException(String message) {
        super(message);
        this.message = message;
    }
}

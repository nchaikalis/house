package com.example.house.exception;

public class AssetSaveException extends RuntimeException {
    private String message;

    public AssetSaveException() {}

    public AssetSaveException(String message) {
        super(message);
        this.message = message;
    }
}

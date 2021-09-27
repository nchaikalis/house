package com.example.house.exception.asset;

public class AssetSaveException extends AssetException {
    private String message;

    public AssetSaveException() {}

    public AssetSaveException(String message) {
        super(message);
        this.message = message;
    }
}

package com.example.house.exception.asset;

public class AssetSearchException extends AssetException {
    private String message;

    public AssetSearchException() {}

    public AssetSearchException(String message) {
        super(message);
        this.message = message;
    }
}

package com.example.house.exception.asset;

public class DeleteAssetException extends AssetException {
    private String message;

    public DeleteAssetException() {}

    public DeleteAssetException(String message) {
        super(message);
        this.message = message;
    }
}
package com.assets.management.exceptions;

public class AssetNotRegistered extends RuntimeException{
    public AssetNotRegistered(String errorMessage) {
        super(errorMessage);
    }
}

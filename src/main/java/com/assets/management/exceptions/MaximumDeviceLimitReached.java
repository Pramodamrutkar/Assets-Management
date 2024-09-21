package com.assets.management.exceptions;

public class MaximumDeviceLimitReached extends RuntimeException{
    public MaximumDeviceLimitReached(String errorMessage) {
        super(errorMessage);
    }
}

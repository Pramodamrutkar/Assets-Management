package com.assets.management.exceptions;

public class ValidationsException extends RuntimeException {
    public ValidationsException(String errorMessage) {
        super(errorMessage);
    }
}

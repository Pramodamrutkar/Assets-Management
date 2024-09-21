package com.assets.management.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationsException.class)
    @ResponseBody
    public ResponseEntity<Object> handleValidationExceptions(ValidationsException validationsException) {
        return new ResponseEntity<>(validationsException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AssetNotRegistered.class)
    @ResponseBody
    public ResponseEntity<Object> handleAssetNotRegistered(AssetNotRegistered assetNotRegistered) {
        return new ResponseEntity<>(assetNotRegistered.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaximumDeviceLimitReached.class)
    @ResponseBody
    public ResponseEntity<Object> handleLimitReached(MaximumDeviceLimitReached maximumDeviceLimitReached) {
        return new ResponseEntity<>(maximumDeviceLimitReached.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

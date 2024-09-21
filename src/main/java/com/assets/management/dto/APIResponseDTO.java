package com.assets.management.dto;

public class APIResponseDTO {
    public Integer statusCode;
    public String message;

    public APIResponseDTO() {
    }

    public APIResponseDTO(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

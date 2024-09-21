package com.assets.management.dto;

public class APISupportResponseDTO {
    private Integer statusCode;
    private String message;
    private Integer ticketId;

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

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public APISupportResponseDTO(Integer statusCode, String message, Integer ticketId) {
        this.statusCode = statusCode;
        this.message = message;
        this.ticketId = ticketId;
    }
}

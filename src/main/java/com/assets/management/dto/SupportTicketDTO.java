package com.assets.management.dto;

import java.util.Date;

public class SupportTicketDTO {
    private Date ticketRaisedOn;
    private String ticketRaisedByEmployee;
    private Integer assetId;
    private String assignedToEmployee;

    public Date getTicketRaisedOn() {
        return ticketRaisedOn;
    }

    public void setTicketRaisedOn(Date ticketRaisedOn) {
        this.ticketRaisedOn = ticketRaisedOn;
    }

    public String getTicketRaisedByEmployee() {
        return ticketRaisedByEmployee;
    }

    public void setTicketRaisedByEmployee(String ticketRaisedByEmployee) {
        this.ticketRaisedByEmployee = ticketRaisedByEmployee;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public String getAssignedToEmployee() {
        return assignedToEmployee;
    }

    public void setAssignedToEmployee(String assignedToEmployee) {
        this.assignedToEmployee = assignedToEmployee;
    }
}

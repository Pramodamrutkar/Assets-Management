package com.assets.management.dto;

import com.assets.management.entity.AssetsRegister;

import java.util.Date;

public class SupportTicketDTOResponse {
    private Integer ticketId;
    private Date ticketRaisedOn;
    private String ticketRaisedByEmployee;
    private AssetsRegister assetId;
    private String assignedToEmployee;
    private Date expectedResolution;
    private String ticketStatus;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

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

    public AssetsRegister getAssetId() {
        return assetId;
    }

    public void setAssetId(AssetsRegister assetId) {
        this.assetId = assetId;
    }

    public String getAssignedToEmployee() {
        return assignedToEmployee;
    }

    public void setAssignedToEmployee(String assignedToEmployee) {
        this.assignedToEmployee = assignedToEmployee;
    }

    public Date getExpectedResolution() {
        return expectedResolution;
    }

    public void setExpectedResolution(Date expectedResolution) {
        this.expectedResolution = expectedResolution;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}

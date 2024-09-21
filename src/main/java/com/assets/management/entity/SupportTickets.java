package com.assets.management.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "support_tickets")
public class SupportTickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    @Column(name = "ticket_raised_on")
    private Date ticketRaisedOn;

    @Column(name = "ticket_raised_by_employee", length = 6)
    private String ticketRaisedByEmployee;

    @ManyToOne(targetEntity = AssetsRegister.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "asset_id")
    private AssetsRegister assetsRegister;

    @Column(name = "assigned_to_employee", length = 6)
    private String assignedToEmployee;

    @Column(name = "expected_resolution")
    private Date expectedResolution;

    @Column(name = "ticket_status",length = 10)
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

    public AssetsRegister getAssetsRegister() {
        return assetsRegister;
    }

    public void setAssetsRegister(AssetsRegister assetsRegister) {
        this.assetsRegister = assetsRegister;
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

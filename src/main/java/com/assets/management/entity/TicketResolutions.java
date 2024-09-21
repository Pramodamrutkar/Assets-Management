package com.assets.management.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ticket_resolutions")
public class TicketResolutions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = SupportTickets.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", referencedColumnName = "ticketId")
    private SupportTickets ticketId;

    @Column(name = "resolution_date")
    private Date resolutionDate;

    @Column(name = "resolution_description", length = 100)
    private String resolutionDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SupportTickets getTicketId() {
        return ticketId;
    }

    public void setTicketId(SupportTickets ticketId) {
        this.ticketId = ticketId;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public String getResolutionDescription() {
        return resolutionDescription;
    }

    public void setResolutionDescription(String resolutionDescription) {
        this.resolutionDescription = resolutionDescription;
    }
}

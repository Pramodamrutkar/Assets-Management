package com.assets.management.repository;

import com.assets.management.entity.SupportTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportTicketsRepository extends JpaRepository<SupportTickets, Integer> {
    List<SupportTickets> findByAssignedToEmployee(String executiveId);

    List<SupportTickets> findByAssignedToEmployeeAndTicketStatus(String executiveId, String resolved);
}

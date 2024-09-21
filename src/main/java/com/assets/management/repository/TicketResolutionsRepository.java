package com.assets.management.repository;

import com.assets.management.entity.TicketResolutions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketResolutionsRepository extends JpaRepository<TicketResolutions, Integer> {
}

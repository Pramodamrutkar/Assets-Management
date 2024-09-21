package com.assets.management.repository;

import com.assets.management.entity.AssetsRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetsRegisterRepository extends JpaRepository<AssetsRegister, Integer> {
    long countByIssuedToEmployee(String issuedToEmployee);
}

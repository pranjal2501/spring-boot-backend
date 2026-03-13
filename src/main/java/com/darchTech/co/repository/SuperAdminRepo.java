package com.darchTech.co.repository;

import com.darchTech.co.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuperAdminRepo extends JpaRepository<SuperAdmin, Long> {
    Optional<SuperAdmin> findBySMail(String Smail);
}

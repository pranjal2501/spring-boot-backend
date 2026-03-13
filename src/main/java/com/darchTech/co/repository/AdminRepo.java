package com.darchTech.co.repository;

import com.darchTech.co.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,Long> {
    Optional<Admin> findByAMail(String mail);
}

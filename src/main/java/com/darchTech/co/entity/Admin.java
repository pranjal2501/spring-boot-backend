package com.darchTech.co.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "admins", uniqueConstraints = {
        @UniqueConstraint(columnNames = "a_mobile_number"),
        @UniqueConstraint(columnNames = "a_mail")
})
@Getter
@Setter
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    private Long id;

    @Column(name = "a_name", nullable = false, length = 100)
    private String name;

    @Column(name = "a_mobile_number", nullable = false, length = 15)
    private String mobileNumber;

    @Column(name = "a_mail", nullable = false, length = 150)
    private String email;

    @Column(name = "a_password", nullable = false)
    private String password;

    @Column(name = "a_live_photo")
    private String livePhoto;

    @Column(name = "a_area", length = 150)
    private String area;

    @Column(name = "a_adhar_no", length = 12)
    private String adharNo; // Standardized spelling

    @Column(name = "a_created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "a_updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "a_is_active")
    private Boolean isActive = true;

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    @PreUpdate
    protected void onUpdate() { updatedAt = LocalDateTime.now(); }
}
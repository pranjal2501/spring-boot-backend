package com.darchTech.co.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
    name = "super_admin",
    @UniqueConstraint = (columnNames = "email"),
    @UniqueContstraint = (columnNames = "mobile_number")
)
@Getter
@Setter
public class SuperAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sId;
    
    @Column(name = "s_name", nullable = false, length = 100)
    private String sName;

    @Column(name = "s_mobile_number", nullable = false, length = 10)
    private String sMobileNumber;

    @Column(name = "s_email", nullable = false, length = 150)
    private String sMail;

    @Column(name = "password", nullable = false)
    private String sPassword;

    @Column(name = "s_photo_url")
    private String sPhotoUrl;

   @Column(name = "s_aadhar_number", length = 12)
    private String sAadhar

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "s_is_active")
    private Boolean sIsActive = true;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

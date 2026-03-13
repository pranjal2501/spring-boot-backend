package com.darchTech.co.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "admins",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "a_mobile_number"),
                @UniqueConstraint(columnNames = "a_mail")
        }
)
@Getter
@Setter
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    private Long aId;

    @Column(name = "a_name", nullable = false, length = 100)
    private String aName;

    @Column(name = "a_mobile_number", nullable = false, length = 15)
    private String aMobileNumber;

    @Column(name = "a_mail", nullable = false, length = 150)
    private String aMail;

    @Column(name = "a_password", nullable = false)
    private String aPassword;

    @Column(name = "a_live_photo")
    private String aLivePhoto;

    @Column(name = "a_area", length = 150)
    private String aArea;

    @Column(name = "a_adhar_no", length = 12)
    private String aAdharNo;

    @Column(name = "a_created_at", updatable = false)
    private LocalDateTime aCreatedAt;

    @Column(name = "a_updated_at")
    private LocalDateTime aUpdatedAt;

    @Column(name = "a_is_active")
    private Boolean aIsActive = true;

    @PrePersist
    protected void onCreate() {
        aCreatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        aUpdatedAt = LocalDateTime.now();
    }
}

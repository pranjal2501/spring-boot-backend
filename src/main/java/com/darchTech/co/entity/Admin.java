package com.darchTech.co.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "admins")
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String mobileNumber;

    @Column(unique = true)
    private String mail;

    @Column(nullable = false)
    private String password;

    private String livePhoto;

    private String area;

    @Column(nullable = true)
    private String adharCard;
}

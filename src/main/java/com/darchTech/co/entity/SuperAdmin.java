package com.darchTech.co.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "super_admin")
@Data
public class SuperAdmin {

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

    private String photo;

    @Column(nullable = true)
    private String aadhaar;
}
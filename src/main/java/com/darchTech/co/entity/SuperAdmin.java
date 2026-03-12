package com.darchTech.co.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "super_admin")
@Data
public class SuperAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sId;

    @Column(nullable = false)
    private String sName;

    @Column(unique = true)
    private String sMobileNumber;

    @Column(unique = true)
    private String sMail;

    @Column(nullable = false)
    private String sPassword;

    private String sPhoto;

    @Column(nullable = true)
    private String sAadhar;
}

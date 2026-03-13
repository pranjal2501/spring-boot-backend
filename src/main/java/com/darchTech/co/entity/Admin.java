package com.darchTech.co.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "admins")
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aId;

    @Column(nullable = false)
    private String aName;

    @Column(unique = true)
    private String aMobileNumber;

    @Column(unique = true)
    private String aMail;

    @Column(nullable = false)
    private String aMassword;

    private String aLivePhoto;

    private String aArea;

    @Column(nullable = true)
    private String aAdharNo;
}

package com.darchTech.co.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterAdminResponseDTO {
    private Long aId;
    private String aMail;
    private String message;
}
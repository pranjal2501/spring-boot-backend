package com.darchTech.co.dto.auth;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAdminRequestDTO {

    @NotBlank(message = "Name is required")
    private String aName;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
    private String aMobileNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String aMail;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String aPassword;

    private String aLivePhoto;

    private String aArea;

    @Pattern(regexp = "^[0-9]{12}$", message = "Invalid Aadhar number")
    private String aAadhaarNo;
}
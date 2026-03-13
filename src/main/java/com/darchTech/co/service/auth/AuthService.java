package com.darchTech.co.service.auth;

import com.darchTech.co.dto.auth.LoginRequestDTO;
import com.darchTech.co.dto.auth.LoginResponseDTO;
import com.darchTech.co.dto.auth.RegisterAdminRequestDTO;
import com.darchTech.co.dto.auth.RegisterAdminResponseDTO;
import jakarta.validation.Valid;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    RegisterAdminResponseDTO createAdmin(RegisterAdminRequestDTO registerAdminDTO);
}

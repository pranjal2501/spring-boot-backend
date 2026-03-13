package com.darchTech.co.service.auth;

import com.darchTech.co.Enum.Role;
import com.darchTech.co.dto.auth.LoginRequestDTO;
import com.darchTech.co.dto.auth.LoginResponseDTO;
import com.darchTech.co.dto.auth.RegisterAdminRequestDTO;
import com.darchTech.co.dto.auth.RegisterAdminResponseDTO;
import com.darchTech.co.entity.Admin;
import com.darchTech.co.entity.SuperAdmin;
import com.darchTech.co.exception.InvalidCredentialsException;
import com.darchTech.co.mapper.auth.AdminMapper;
import com.darchTech.co.repository.AdminRepo;
import com.darchTech.co.repository.SuperAdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final SuperAdminRepo superAdminRepo;
    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;
    private final AdminMapper adminMapper;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        String email = loginRequestDTO.getEmail();
        String password = loginRequestDTO.getPassword();

        // 1. Check SuperAdmin
        Optional<SuperAdmin> superAdminOpt = superAdminRepo.findByEmail(email);
        if (superAdminOpt.isPresent()) {
            SuperAdmin superAdmin = superAdminOpt.get();
            // Assuming SuperAdmin entity field is 'sPassword'
            if (!passwordEncoder.matches(password, superAdmin.getSPassword())) {
                throw new InvalidCredentialsException("Invalid password");
            }
            return new LoginResponseDTO(superAdmin.getEmail(), Role.SUPER_ADMIN);
        }

        // 2. Check Admin
        Optional<Admin> adminOpt = adminRepo.findByEmail(email);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            // Assuming Admin entity field is 'aPassword'
            if (!passwordEncoder.matches(password, admin.getPassword())) {
                throw new InvalidCredentialsException("Invalid password");
            }
            return new LoginResponseDTO(email, Role.ADMIN);
        }

        throw new InvalidCredentialsException("Invalid email or password");
    }

    @Override
    public RegisterAdminResponseDTO createAdmin(RegisterAdminRequestDTO registerAdminDTO) {
        Admin admin = adminMapper.toEntity(registerAdminDTO);
        String rawPassword = registerAdminDTO.getPassword();

        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        admin.setPassword(passwordEncoder.encode(rawPassword));
        Admin savedAdmin = adminRepo.save(admin);
        return adminMapper.toResponse(savedAdmin);
    }
}
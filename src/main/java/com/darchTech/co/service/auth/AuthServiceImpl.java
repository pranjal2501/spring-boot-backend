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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final SuperAdminRepo superAdminRepo;
    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;
    private final AdminMapper adminMapper;
    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        String email = loginRequestDTO.getEmail();
        String password = loginRequestDTO.getPassword();
        Optional<SuperAdmin> superAdminOpt = superAdminRepo.findBySMail(email);

        if(superAdminOpt.isPresent()){
            SuperAdmin superAdmin = superAdminOpt.get();
            if(!passwordEncoder.matches(password,superAdmin.getSPassword())){
                throw new InvalidCredentialsException("Invalid password");
            }
            return new LoginResponseDTO(superAdmin.getSMail(), Role.SUPER_ADMIN);
        }

        Optional<Admin> adminOpt = adminRepo.findByAMail(email);

        if(adminOpt.isPresent()){
            Admin admin = adminOpt.get();
            if(!passwordEncoder.matches(password,admin.getAPassword())){
                throw new InvalidCredentialsException("Invalid password");
            }
            return new LoginResponseDTO(email,Role.ADMIN);
        }
        throw new InvalidCredentialsException("Invalid email or password");
    }

    @Override
    public RegisterAdminResponseDTO createAdmin(RegisterAdminRequestDTO registerAdminDTO) {
        Admin admin = adminMapper.toEntity(registerAdminDTO);
        admin.setAPassword(passwordEncoder.encode(admin.getAPassword()));
        adminRepo.save(admin);
        return adminMapper.toResponse(admin);
    }
}

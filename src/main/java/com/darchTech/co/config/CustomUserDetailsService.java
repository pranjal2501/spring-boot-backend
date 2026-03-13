package com.darchTech.co.config;

import com.darchTech.co.repository.AdminRepo;
import com.darchTech.co.repository.SuperAdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepo adminRepo;
    private final SuperAdminRepo superAdminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Check SuperAdmin table first
        var superAdmin = superAdminRepo.findByEmail(email);
        if (superAdmin.isPresent()) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(superAdmin.get().getEmail())
                    .password(superAdmin.get().getSPassword()) // Using your Entity's password field
                    .roles("SUPER_ADMIN")
                    .build();
        }

        // 2. Check Admin table
        var admin = adminRepo.findByEmail(email);
        if (admin.isPresent()) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(admin.get().getEmail())
                    .password(admin.get().getPassword()) // Using your Entity's password field
                    .roles("ADMIN")
                    .build();
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
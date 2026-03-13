package com.darchTech.co.controller.auth;

import com.darchTech.co.dto.auth.LoginRequestDTO;
import com.darchTech.co.dto.auth.LoginResponseDTO;
import com.darchTech.co.dto.auth.RegisterAdminRequestDTO;
import com.darchTech.co.dto.auth.RegisterAdminResponseDTO;
import com.darchTech.co.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
   private AuthService authService;

   @PostMapping("/login")
   public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
      return ResponseEntity.ok(authService.login(loginRequestDTO));
   }

   @PostMapping("/register/admin")
   public ResponseEntity<RegisterAdminResponseDTO> register(@Valid @RequestBody RegisterAdminRequestDTO registerAdminDTO){
      return ResponseEntity.status(HttpStatus.CREATED).body(authService.createAdmin(registerAdminDTO));
   }


}

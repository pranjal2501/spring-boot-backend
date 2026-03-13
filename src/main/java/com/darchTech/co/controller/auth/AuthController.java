package com.darchTech.co.controller.auth;

import com.darchTech.co.dto.auth.LoginRequestDTO;
import com.darchTech.co.dto.auth.LoginResponseDTO;
import com.darchTech.co.dto.auth.RegisterAdminRequestDTO;
import com.darchTech.co.dto.auth.RegisterAdminResponseDTO;
import com.darchTech.co.service.auth.AuthService;
import com.darchTech.co.utilities.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

   private final AuthService authService;
   private final AuthenticationManager authManager;
   private final JwtService jwtService;
   private final UserDetailsService userDetailsService; // Added this injection

   @PostMapping("/login")
   public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO request) {
      try {
         // 1. Authenticate using Spring Security's manager
         // Note: We use request.getEmail() as the principal (username)
         authManager.authenticate(
                 new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
         );

         // 2. If authentication didn't throw an exception, load the user
         UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

         // 3. Generate the JWT
         String token = jwtService.generateToken(userDetails);

         // 4. Return the token (You can use your LoginResponseDTO here instead of a Map)
         return ResponseEntity.ok(Map.of(
                 "token", token,
                 "email", userDetails.getUsername()
         ));

      } catch (BadCredentialsException e) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
      }
   }

   @PostMapping("/register/admin")
   public ResponseEntity<RegisterAdminResponseDTO> register(@Valid @RequestBody RegisterAdminRequestDTO registerAdminDTO) {
      return ResponseEntity.status(HttpStatus.CREATED).body(authService.createAdmin(registerAdminDTO));
   }
}
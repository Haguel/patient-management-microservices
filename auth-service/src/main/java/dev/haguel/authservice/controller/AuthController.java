package dev.haguel.authservice.controller;

import dev.haguel.authservice.dto.LoginRequestDTO;
import dev.haguel.authservice.dto.LoginResponseDTO;
import dev.haguel.authservice.service.AuthService;
import dev.haguel.authservice.util.EndPoints;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Generate JWT token on user login")
    @PostMapping(EndPoints.Auth.LOGIN)
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        String token = authService.authenticate(loginRequestDTO).orElse(null);

        if(token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(LoginResponseDTO.builder().token(token).build());
    }
}

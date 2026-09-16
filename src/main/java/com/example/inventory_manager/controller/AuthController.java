package com.example.inventory_manager.controller;

import com.example.inventory_manager.dto.request.LoginRequest;
import com.example.inventory_manager.dto.request.RegisterRequest;
import com.example.inventory_manager.dto.response.AddUserResponse;
import com.example.inventory_manager.dto.response.LoginResponse;
import com.example.inventory_manager.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AddUserResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        AddUserResponse addUserResponse = authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(addUserResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }
}

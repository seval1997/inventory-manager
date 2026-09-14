package com.example.inventory_manager.service;

import com.example.inventory_manager.dto.request.LoginRequest;
import com.example.inventory_manager.dto.request.RegisterRequest;
import com.example.inventory_manager.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}

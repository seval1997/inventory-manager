package com.example.inventory_manager.service;

import com.example.inventory_manager.dto.request.LoginRequest;
import com.example.inventory_manager.dto.request.RegisterRequest;
import com.example.inventory_manager.dto.response.AddUserResponse;
import com.example.inventory_manager.dto.response.LoginResponse;

public interface AuthService {
    AddUserResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}

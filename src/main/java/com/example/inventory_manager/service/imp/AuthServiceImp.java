package com.example.inventory_manager.service.imp;

import com.example.inventory_manager.dto.request.LoginRequest;
import com.example.inventory_manager.dto.request.RegisterRequest;
import com.example.inventory_manager.dto.response.AddUserResponse;
import com.example.inventory_manager.dto.response.LoginResponse;
import com.example.inventory_manager.entity.User;
import com.example.inventory_manager.entity.enums.Role;
import com.example.inventory_manager.exception.GlobalExceptionHandler;
import com.example.inventory_manager.repository.UserRepository;
import com.example.inventory_manager.security.JwtUtil;
import com.example.inventory_manager.service.AuthService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final GlobalExceptionHandler exceptionHandler;

    public AuthServiceImp(UserRepository userRepository, JwtUtil jwtUtil, GlobalExceptionHandler exceptionHandler) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public AddUserResponse register(RegisterRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setCreateAt(LocalDateTime.now());
        userRepository.save(user);
        return new AddUserResponse(user.getUsername(), user.getRole().name());

    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid Username or Password"));

        if(!user.getPassword().matches(request.getPassword())) {
            throw new BadCredentialsException("Invalid Username or Password");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        return new LoginResponse(token, user.getUsername(), user.getRole().name());
    }
}

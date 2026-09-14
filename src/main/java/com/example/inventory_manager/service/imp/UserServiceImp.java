package com.example.inventory_manager.service.imp;

import com.example.inventory_manager.config.SecurityConfig;
import com.example.inventory_manager.dto.request.ChangePasswordRequest;
import com.example.inventory_manager.dto.request.UpdateProfileRequest;
import com.example.inventory_manager.dto.response.UserResponse;
import com.example.inventory_manager.entity.User;
import com.example.inventory_manager.repository.UserRepository;
import com.example.inventory_manager.security.JwtUtil;
import com.example.inventory_manager.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;

    public UserServiceImp(UserRepository userRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    @Override
    public UserResponse getMyProfile() {
        return null;
    }

    @Override
    public UserResponse getProfile(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid Username"));
        return new UserResponse(user.getUsername(), user.getEmail(), user.getRole());
    }

    @Override
    public UserResponse updateMyProfile(UpdateProfileRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid User"));
        user.setEmail(request.getEmail());
        userRepository.save(user);
        return new UserResponse(user.getUsername(), user.getEmail(), user.getRole());
    }

    @Override
    public void changeMyPassword(ChangePasswordRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid User"));
        if(user.getPassword().matches(request.getCurrentPassword())
                && !request.getCurrentPassword().equals(request.getNewPassword())) {
            user.setPassword(request.getNewPassword());
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Invalid Password!");
        }
    }
}

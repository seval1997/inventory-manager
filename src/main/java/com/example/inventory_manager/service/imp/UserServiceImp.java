package com.example.inventory_manager.service.imp;

import com.example.inventory_manager.dto.request.ChangePasswordRequest;
import com.example.inventory_manager.dto.request.UpdateProfileRequest;
import com.example.inventory_manager.dto.response.UserResponse;
import com.example.inventory_manager.entity.User;
import com.example.inventory_manager.repository.UserRepository;
import com.example.inventory_manager.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return null;
    }

    @Override
    public void changeMyPassword(ChangePasswordRequest request) {

    }
}

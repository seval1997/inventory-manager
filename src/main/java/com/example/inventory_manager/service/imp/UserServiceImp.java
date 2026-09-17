package com.example.inventory_manager.service.imp;

import com.example.inventory_manager.config.SecurityConfig;
import com.example.inventory_manager.dto.request.ChangePasswordRequest;
import com.example.inventory_manager.dto.request.UpdateProfileRequest;
import com.example.inventory_manager.dto.response.UserResponse;
import com.example.inventory_manager.entity.User;
import com.example.inventory_manager.entity.enums.Role;
import com.example.inventory_manager.repository.UserRepository;
import com.example.inventory_manager.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserResponse getMyProfile(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid Username"));
        return new UserResponse(user.getUsername(), user.getEmail(), user.getRole());
    }

    @Override
    public UserResponse updateMyProfile(String username, UpdateProfileRequest request) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid User"));
        user.setEmail(request.getEmail());
        userRepository.save(user);
        return new UserResponse(user.getUsername(), user.getEmail(), user.getRole());
    }

    @Override
    public void changeMyPassword(String username, ChangePasswordRequest request) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid User"));
        if(user.getPassword().matches(request.getCurrentPassword())
                && !request.getCurrentPassword().equals(request.getNewPassword())) {
            user.setPassword(request.getNewPassword());
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Invalid Password!");
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse changeUserRole(Long id,  Role role) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
        user.setRole(role);
        userRepository.save(user);
        return new UserResponse(user.getUsername(), user.getEmail(), user.getRole());
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(user -> new UserResponse(user.getUsername(), user.getEmail(), user.getRole()));
    }
}

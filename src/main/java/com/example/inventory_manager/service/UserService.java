package com.example.inventory_manager.service;

import com.example.inventory_manager.dto.request.ChangePasswordRequest;
import com.example.inventory_manager.dto.request.UpdateProfileRequest;
import com.example.inventory_manager.dto.response.UserResponse;
import com.example.inventory_manager.entity.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponse getMyProfile();
    UserResponse getProfile(String username);
    UserResponse updateMyProfile(UpdateProfileRequest request);
    void changeMyPassword(ChangePasswordRequest request);

    void deleteUser(Long id);
    UserResponse changeUserRole(Long id, Role role);
    Page<UserResponse> getAllUsers(Pageable pageable);

}

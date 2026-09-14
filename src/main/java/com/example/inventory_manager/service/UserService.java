package com.example.inventory_manager.service;

import com.example.inventory_manager.dto.request.ChangePasswordRequest;
import com.example.inventory_manager.dto.request.UpdateProfileRequest;
import com.example.inventory_manager.dto.response.UserResponse;

public interface UserService {

    UserResponse getMyProfile();
    UserResponse getProfile(String username);
    UserResponse updateMyProfile(UpdateProfileRequest request);
    void changeMyPassword(ChangePasswordRequest request);

}

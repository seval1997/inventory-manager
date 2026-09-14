package com.example.inventory_manager.controller;

import com.example.inventory_manager.dto.request.ChangePasswordRequest;
import com.example.inventory_manager.dto.request.UpdateProfileRequest;
import com.example.inventory_manager.dto.response.MessageResponse;
import com.example.inventory_manager.dto.response.UserResponse;
import com.example.inventory_manager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserResponse userResponse = userService.getProfile(username);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PatchMapping("/me")
    public ResponseEntity<UserResponse> updateMyProfile(@Valid @RequestBody UpdateProfileRequest updateProfileRequest){
        UserResponse userResponse = userService.updateMyProfile(updateProfileRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("/me/password")
    public ResponseEntity<MessageResponse> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        userService.changeMyPassword(changePasswordRequest);
        MessageResponse response = new MessageResponse();
        response.setMessage("Password changed successfully.");
        return ResponseEntity.ok(response);
    }




}

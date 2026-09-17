package com.example.inventory_manager.controller;

import com.example.inventory_manager.dto.request.ChangePasswordRequest;
import com.example.inventory_manager.dto.request.UpdateProfileRequest;
import com.example.inventory_manager.dto.response.MessageResponse;
import com.example.inventory_manager.dto.response.UserResponse;
import com.example.inventory_manager.service.UserService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyProfile() {
        String username = getAuthenticatedUser();
        UserResponse userResponse = userService.getMyProfile(username);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PatchMapping("/me")
    public ResponseEntity<UserResponse> updateMyProfile(@Valid @RequestBody UpdateProfileRequest updateProfileRequest){
        String username = getAuthenticatedUser();
        UserResponse userResponse = userService.updateMyProfile(username, updateProfileRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("/me/password")
    public ResponseEntity<MessageResponse> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        String username = getAuthenticatedUser();
        userService.changeMyPassword(username, changePasswordRequest);
        MessageResponse response = new MessageResponse();
        response.setMessage("Password changed successfully.");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("User deleted");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserResponse>> getAllUsers(Pageable pageable) {
        getAuthenticatedUser();
        Page<UserResponse> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    public String getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User not authenticated") {
            };
        }
        return authentication.getName();
    }




}

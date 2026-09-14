package com.example.inventory_manager.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChangePasswordRequest {
    @NotBlank(message = "Password can't be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String currentPassword;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String newPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

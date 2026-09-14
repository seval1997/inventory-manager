package com.example.inventory_manager.dto.request;

import jakarta.validation.constraints.Email;

public class UpdateProfileRequest {

    @Email(message = "Email is invalid")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

package com.example.inventory_manager.dto.request;

import jakarta.validation.constraints.Email;

public class UpdateProfileRequest {

    @Email(message = "Email is invalid")
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

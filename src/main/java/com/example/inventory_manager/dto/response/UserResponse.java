package com.example.inventory_manager.dto.response;

import com.example.inventory_manager.entity.enums.Role;

public class UserResponse {

    String username;
    String email;
    Role role;

    public UserResponse( String username, String email, Role role) {
        this.username = username;
        this.role = role;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

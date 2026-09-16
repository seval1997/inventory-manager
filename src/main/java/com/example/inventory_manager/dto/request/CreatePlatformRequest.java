package com.example.inventory_manager.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreatePlatformRequest {

    @NotBlank(message = "Platform name can not be empty")
    String name;

    @NotBlank(message = "Platform description can not be empty")
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

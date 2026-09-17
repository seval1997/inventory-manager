package com.example.inventory_manager.dto.request;

import com.example.inventory_manager.entity.enums.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;

import java.time.LocalDateTime;

public class CreateEventRequest {

    @NotBlank(message = "Name can't be empty")
    String name;

    @NotNull(message = "Type can't be empty")
    EventType type;

    @NotNull(message = "Start date is required")
    LocalDateTime startDate;

    @NotNull(message = "End date is required")
    LocalDateTime endDate;

    @NotNull
    Long platformId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }
}

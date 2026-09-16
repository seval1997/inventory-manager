package com.example.inventory_manager.service;

import com.example.inventory_manager.dto.request.CreatePlatformRequest;
import com.example.inventory_manager.dto.request.UpdatePlatformRequest;
import com.example.inventory_manager.dto.response.PlatformResponse;
import com.example.inventory_manager.entity.Platform;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface PlatformService {

    Page<PlatformResponse> getAllPlatform(Pageable pageable);

    PlatformResponse getPlatform(Long id);

    PlatformResponse addPlatform(CreatePlatformRequest request);

    PlatformResponse updatePlatform(UpdatePlatformRequest request);

    void deletePlatform(Long id);

}

package com.example.inventory_manager.controller;

import com.example.inventory_manager.dto.request.CreatePlatformRequest;
import com.example.inventory_manager.dto.request.UpdatePlatformRequest;
import com.example.inventory_manager.dto.response.PlatformResponse;
import com.example.inventory_manager.entity.Platform;
import com.example.inventory_manager.service.PlatformService;
import com.example.inventory_manager.service.imp.PlatformServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/platform")
public class PlatformController {

    private final PlatformService platformService;

    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    public ResponseEntity<Page<PlatformResponse>> getAllPlatform(Pageable pageable) {
        Page<PlatformResponse> platformResponsePage =  platformService.getAllPlatform(pageable);
        return ResponseEntity.ok(platformResponsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatformResponse> getPlatform(@PathVariable Long id) {
        PlatformResponse response = platformService.getPlatform(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<PlatformResponse> createPlatform(@Valid @RequestBody CreatePlatformRequest request) {
        PlatformResponse platformResponse = platformService.addPlatform(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(platformResponse);
    }

    @PatchMapping("/update")
    public ResponseEntity<PlatformResponse> updatePlatform(@RequestBody UpdatePlatformRequest request) {
        PlatformResponse platformResponse = platformService.updatePlatform(request);
        return ResponseEntity.ok(platformResponse);
    }

    @DeleteMapping("/{id}")
    public void deletePlatform(@PathVariable Long id) {
        platformService.deletePlatform(id);
    }



}

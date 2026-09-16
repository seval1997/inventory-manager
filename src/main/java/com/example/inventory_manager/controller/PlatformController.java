package com.example.inventory_manager.controller;

import com.example.inventory_manager.dto.response.PlatformResponse;
import com.example.inventory_manager.service.PlatformService;
import com.example.inventory_manager.service.imp.PlatformServiceImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}

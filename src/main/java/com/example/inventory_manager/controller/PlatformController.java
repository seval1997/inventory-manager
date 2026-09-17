package com.example.inventory_manager.controller;

import com.example.inventory_manager.dto.request.CreatePlatformRequest;
import com.example.inventory_manager.dto.request.UpdatePlatformRequest;
import com.example.inventory_manager.dto.response.PlatformResponse;
import com.example.inventory_manager.entity.Platform;
import com.example.inventory_manager.service.PlatformService;
import com.example.inventory_manager.service.imp.PlatformServiceImp;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/platform")
public class PlatformController {

    private final PlatformService platformService;
    private final ControllerUtil controllerUtil;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public PlatformController(PlatformService platformService, ControllerUtil controllerUtil) {
        this.platformService = platformService;
        this.controllerUtil = controllerUtil;
    }

    @GetMapping
    public ResponseEntity<Page<PlatformResponse>> getAllPlatform(Pageable pageable) {
        controllerUtil.getAuthenticatedUser();
        Page<PlatformResponse> platformResponsePage =  platformService.getAllPlatform(pageable);
        return ResponseEntity.ok(platformResponsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatformResponse> getPlatform(@PathVariable Long id) {
        controllerUtil.getAuthenticatedUser();
        PlatformResponse response = platformService.getPlatform(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PlatformResponse> createPlatform(@Valid @RequestBody CreatePlatformRequest request) {
        PlatformResponse platformResponse = platformService.addPlatform(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(platformResponse);
    }

    @PatchMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PlatformResponse> updatePlatform(@RequestBody UpdatePlatformRequest request) {
        PlatformResponse platformResponse = platformService.updatePlatform(request);
        return ResponseEntity.ok(platformResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePlatform(@PathVariable Long id) {
        platformService.deletePlatform(id);
    }

}

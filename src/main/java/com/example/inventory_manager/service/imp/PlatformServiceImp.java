package com.example.inventory_manager.service.imp;

import com.example.inventory_manager.dto.request.CreatePlatformRequest;
import com.example.inventory_manager.dto.request.UpdatePlatformRequest;
import com.example.inventory_manager.dto.response.PlatformResponse;
import com.example.inventory_manager.entity.Platform;
import com.example.inventory_manager.repository.PlatformRepository;
import com.example.inventory_manager.repository.UserRepository;
import com.example.inventory_manager.service.PlatformService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlatformServiceImp implements PlatformService {

    private final PlatformRepository platformRepository;

    public PlatformServiceImp(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @Override
    public Page<PlatformResponse> getAllPlatform(Pageable pageable) {
        return platformRepository.findAll(pageable)
                .map(user -> new PlatformResponse(user.getId(), user.getName(), user.getDescription()));
    }

    @Override
    public PlatformResponse getPlatform(Long id) {
        Platform platform = platformRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Platform not found"));
        return new PlatformResponse(platform.getId(), platform.getName(), platform.getDescription());
    }

    @Override
    public PlatformResponse addPlatform(CreatePlatformRequest request) {
        Platform platform = new Platform();
        platform.setName(request.getName());
        platform.setDescription(request.getDescription());
        if (platformRepository.existsByName(request.getName()))
            throw new IllegalArgumentException("Platform already exists.");

        platformRepository.save(platform);
        return new PlatformResponse(request.getName(), request.getDescription());
    }

    @Override
    public PlatformResponse updatePlatform(UpdatePlatformRequest request) {
        Platform platform = platformRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Platform not found!"));
        if (request.getName() != null && !request.getName().isBlank())
            platform.setName(request.getName());

        if (request.getDescription() != null && !request.getDescription().isBlank())
            platform.setDescription(request.getDescription());

        platformRepository.save(platform);
        return new PlatformResponse(platform.getId(), platform.getName(), platform.getDescription());
    }

    @Override
    public void deletePlatform(Long id) {
        platformRepository.deleteById(id);
    }
}

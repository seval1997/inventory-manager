package com.example.inventory_manager.repository;

import com.example.inventory_manager.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Long> {

    boolean existsByName(String name);

}

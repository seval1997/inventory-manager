package com.example.inventory_manager.repository;

import com.example.inventory_manager.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}

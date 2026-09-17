package com.example.inventory_manager.repository;

import com.example.inventory_manager.entity.Event;
import com.example.inventory_manager.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findByEndDateBefore(LocalDateTime dateTime, Pageable pageable);
    Page<Event> findByEndDateAfter(LocalDateTime dateTime, Pageable pageable);
    Page<Event> findByCreatedBy(User user, Pageable pageable);

}

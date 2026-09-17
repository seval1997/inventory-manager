package com.example.inventory_manager.service;

import com.example.inventory_manager.dto.request.CreateEventRequest;
import com.example.inventory_manager.dto.response.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    EventResponse createEvent(String username, CreateEventRequest eventRequest);
    Page<EventResponse> getAllEvents(Pageable pageable);

}

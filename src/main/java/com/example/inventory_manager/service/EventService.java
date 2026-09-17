package com.example.inventory_manager.service;

import com.example.inventory_manager.dto.request.CreateEventRequest;
import com.example.inventory_manager.dto.request.UpdateEventRequest;
import com.example.inventory_manager.dto.response.EventResponse;
import com.example.inventory_manager.dto.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    EventResponse createEvent(String username, CreateEventRequest eventRequest);
    Page<EventResponse> getAllEvents(Pageable pageable);
    Page<EventResponse> getAllCompletedEvents(Pageable pageable);
    Page<EventResponse> getAllActiveEvents(Pageable pageable);
    Page<EventResponse> getMyEvents(String username, Pageable pageable);
    EventResponse   updateEvent(String username, Long eventId, UpdateEventRequest eventRequest);
    MessageResponse deleteEvent(String username, Long id);

}

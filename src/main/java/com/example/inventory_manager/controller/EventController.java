package com.example.inventory_manager.controller;

import com.example.inventory_manager.dto.request.CreateEventRequest;
import com.example.inventory_manager.dto.request.UpdateEventRequest;
import com.example.inventory_manager.dto.response.EventResponse;
import com.example.inventory_manager.dto.response.MessageResponse;
import com.example.inventory_manager.entity.Event;
import com.example.inventory_manager.service.EventService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final ControllerUtil controllerUtil;

    public EventController(EventService eventService, ControllerUtil util) {
        this.eventService = eventService;
        this.controllerUtil = util;
    }

    @PostMapping
    ResponseEntity<EventResponse> createEvent(@Valid @RequestBody CreateEventRequest eventRequest) {
        String username = controllerUtil.getAuthenticatedUser();
        EventResponse response = eventService.createEvent(username, eventRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    ResponseEntity<Page<EventResponse>> getAllEvents(Pageable pageable){
        controllerUtil.getAuthenticatedUser();
        Page<EventResponse> eventResponses = eventService.getAllEvents(pageable);
        return ResponseEntity.ok(eventResponses);
    }

    @GetMapping("/completed")
    ResponseEntity<Page<EventResponse>> getCompletedEvents(Pageable pageable) {
        controllerUtil.getAuthenticatedUser();
        Page<EventResponse> eventResponses = eventService.getAllCompletedEvents(pageable);
        return ResponseEntity.ok(eventResponses);
    }

    @GetMapping("/active")
    ResponseEntity<Page<EventResponse>> getActiveEvents(Pageable pageable) {
        controllerUtil.getAuthenticatedUser();
        Page<EventResponse> eventResponses = eventService.getAllActiveEvents(pageable);
        return ResponseEntity.ok(eventResponses);
    }

    @GetMapping("/myEvents")
    ResponseEntity<Page<EventResponse>> getMyEvents(Pageable pageable) {
        String username = controllerUtil.getAuthenticatedUser();
        Page<EventResponse> eventResponses = eventService.getMyEvents(username, pageable);
        return ResponseEntity.ok(eventResponses);
    }

    @PatchMapping("/{id}")
    ResponseEntity<EventResponse> updateEvent(@PathVariable Long id, @RequestBody UpdateEventRequest eventRequest) {
        String username = controllerUtil.getAuthenticatedUser();
        EventResponse response = eventService.updateEvent(username, id, eventRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<MessageResponse> deleteEvent(@PathVariable Long id) {
        String username = controllerUtil.getAuthenticatedUser();
        MessageResponse messageResponse = eventService.deleteEvent(username, id);
        return ResponseEntity.ok(messageResponse);
    }



}

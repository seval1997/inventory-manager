package com.example.inventory_manager.controller;

import com.example.inventory_manager.dto.request.CreateEventRequest;
import com.example.inventory_manager.dto.response.EventResponse;
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


}

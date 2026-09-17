package com.example.inventory_manager.service.imp;

import com.example.inventory_manager.controller.ControllerUtil;
import com.example.inventory_manager.dto.request.CreateEventRequest;
import com.example.inventory_manager.dto.request.UpdateEventRequest;
import com.example.inventory_manager.dto.response.EventResponse;
import com.example.inventory_manager.dto.response.MessageResponse;
import com.example.inventory_manager.entity.Event;
import com.example.inventory_manager.entity.Platform;
import com.example.inventory_manager.entity.User;
import com.example.inventory_manager.repository.EventRepository;
import com.example.inventory_manager.repository.PlatformRepository;
import com.example.inventory_manager.repository.UserRepository;
import com.example.inventory_manager.service.EventService;
import com.example.inventory_manager.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventServiceImp implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final PlatformRepository platformRepository;

    public EventServiceImp(EventRepository repository, UserRepository userRepository, PlatformRepository platformRepository) {
        this.eventRepository = repository;
        this.userRepository = userRepository;
        this.platformRepository = platformRepository;
    }

    @Override
    public EventResponse createEvent(String username, CreateEventRequest eventRequest) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid Username"));
        Platform platform = platformRepository.findById(eventRequest.getPlatformId()).orElseThrow(() -> new IllegalArgumentException("Invalid platform Id"));
        Event event = new Event();
        event.setName(eventRequest.getName());
        event.setType(eventRequest.getType());
        event.setCreatedBy(user);
        event.setPlatformId(platform);
        event.setStartDate(eventRequest.getStartDate());
        event.setEndDate(eventRequest.getEndDate());
        eventRepository.save(event);
        return new EventResponse(event.getId(), event.getName(), event.getType().name(), event.getStartDate(), event.getEndDate(), event.getCreatedBy().getId(), event.getPlatformId().getId());
    }

    @Override
    public Page<EventResponse> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable)
                .map(event -> new EventResponse(event.getId(), event.getName(), event.getType().name(), event.getStartDate(), event.getEndDate(), event.getCreatedBy().getId(), event.getPlatformId().getId()));
    }

    @Override
    public Page<EventResponse> getAllCompletedEvents(Pageable pageable) {
        return eventRepository.findByEndDateBefore(LocalDateTime.now(), pageable)
                .map(event -> new EventResponse(event.getId(), event.getName(), event.getType().name(), event.getStartDate(), event.getEndDate(), event.getCreatedBy().getId(), event.getPlatformId().getId()));
    }

    @Override
    public Page<EventResponse> getAllActiveEvents(Pageable pageable) {
        return eventRepository.findByEndDateAfter(LocalDateTime.now(), pageable)
                .map(event -> new EventResponse(event.getId(), event.getName(), event.getType().name(), event.getStartDate(), event.getEndDate(), event.getCreatedBy().getId(), event.getPlatformId().getId()));
    }

    @Override
    public Page<EventResponse> getMyEvents(String username, Pageable pageable) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid Username"));
        return eventRepository.findByCreatedBy(user, pageable)
                .map(event -> new EventResponse(event.getId(), event.getName(), event.getType().name(), event.getStartDate(), event.getEndDate(), event.getCreatedBy().getId(), event.getPlatformId().getId()));
    }

    @Override
    public EventResponse updateEvent(String username, Long eventId, UpdateEventRequest eventRequest) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id"));

        if (!event.getCreatedBy().getUsername().matches(username)) {
            throw new InsufficientAuthenticationException("Unauthorized to update event.");
        }

        Platform platform = platformRepository.findById(event.getPlatformId().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid platform Id"));

        if (eventRequest.getName() != null && !eventRequest.getName().isBlank())
            event.setName(eventRequest.getName());

        if (eventRequest.getType() != null && !eventRequest.getType().name().isBlank())
            event.setType(eventRequest.getType());

        if(eventRequest.getStartDate() != null)
            event.setStartDate(eventRequest.getStartDate());

        if(eventRequest.getEndDate() != null)
            event.setEndDate(eventRequest.getEndDate());

        if(eventRequest.getPlatformId() != null)
            platform = platformRepository.findById(eventRequest.getPlatformId())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid Platform Id"));

        event.setPlatformId(platform);

        Event updatedEvent = eventRepository.save(event);

        return new EventResponse(event.getId(), event.getName(), event.getType().name(), event.getStartDate(), event.getEndDate(), event.getCreatedBy().getId(), event.getPlatformId().getId());
    }

    @Override
    public MessageResponse deleteEvent(String username, Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id"));

        if(!event.getCreatedBy().getUsername().matches(username)) {
            throw new InsufficientAuthenticationException(("Unauthorized to delete event."));
        }

        eventRepository.deleteById(id);

        return new MessageResponse("Event deleted successfully");
    }
}

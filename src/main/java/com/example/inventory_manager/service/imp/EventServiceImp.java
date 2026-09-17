package com.example.inventory_manager.service.imp;

import com.example.inventory_manager.controller.ControllerUtil;
import com.example.inventory_manager.dto.request.CreateEventRequest;
import com.example.inventory_manager.dto.response.EventResponse;
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
import org.springframework.stereotype.Service;

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
                .map(event -> new EventResponse(event.getId(),  event.getName(), event.getType().name(), event.getStartDate(), event.getEndDate(), event.getCreatedBy().getId(), event.getPlatformId().getId()));
    }
}

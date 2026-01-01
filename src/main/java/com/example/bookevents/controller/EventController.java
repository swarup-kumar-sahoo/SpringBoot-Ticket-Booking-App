package com.example.bookevents.controller;


import com.example.bookevents.model.Event;
import com.example.bookevents.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getActiveEvents() {
        return eventService.getActiveEvents();
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable String id) {
        return eventService.getEventById(id);
    }

    public static class AuthController {
    }
}


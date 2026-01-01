package com.example.bookevents.service;

import com.example.bookevents.model.Event;
import com.example.bookevents.model.Gallery;
import com.example.bookevents.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event){

        for (Gallery gallery: event.getGalleries()){
            gallery.setAvailableSeats(gallery.getTotalSeats());
        }

        event.setStatus("ACTIVE");
        event.setCreateAt(LocalDateTime.now());
        return eventRepository.save(event);
    }

    // Admin: Get all events
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    // User/Admin: Get event by ID
    public Event getEventById(String id){
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event Not Found"));
    }

    // Admin: Update event
    public Event updateEvent(String id, Event updateEvent){
        Event existing = getEventById(id);

        existing.setTitle(updateEvent.getTitle());
        existing.setVenue(updateEvent.getVenue());
        existing.setCity(updateEvent.getCity());
        existing.setDate(updateEvent.getDate());
        existing.setGalleries(updateEvent.getGalleries());

        return eventRepository.save(existing);
    }

    // Admin: Delete event
    public void deleteEvent(String id){
        Event event = getEventById(id);
        event.setStatus("CANCEL");
        eventRepository.save(event);
    }

    // User: Get active events
    public  List<Event> getActiveEvents(){
        return eventRepository.findByStatus("ACTIVE");
    }

}

package com.example.bookevents.repository;

import com.example.bookevents.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByStatus(String status);
}

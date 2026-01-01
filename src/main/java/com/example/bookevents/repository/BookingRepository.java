package com.example.bookevents.repository;

import com.example.bookevents.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findUserById(String userId);
}

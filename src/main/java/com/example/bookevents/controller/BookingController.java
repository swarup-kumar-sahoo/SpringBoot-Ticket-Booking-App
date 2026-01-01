package com.example.bookevents.controller;


import com.example.bookevents.model.Booking;
import com.example.bookevents.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    // STEP 1: Create booking (PENDING_PAYMENT)
    @PostMapping("/create")
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    // STEP 3: Get booking details
    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable String id) {
        return bookingService.getBookingById(id);
    }

    // User bookings
    @GetMapping("/users/{userId}")
    public List<Booking> getUserBookings(@PathVariable String userId) {
        return bookingService.getUserBookings(userId);
    }
}


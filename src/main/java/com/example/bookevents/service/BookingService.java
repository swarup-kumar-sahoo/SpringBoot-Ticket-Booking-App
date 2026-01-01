package com.example.bookevents.service;

import com.example.bookevents.model.Booking;
import com.example.bookevents.model.Event;
import com.example.bookevents.model.Gallery;
import com.example.bookevents.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookingRepository bookingRepository;

    // STEP 1: CREATE BOOKING (PENDING_PAYMENT)
    public Booking createBooking(Booking booking) {

        String eventId = booking.getEventId();
        String galleryNo = booking.getGalleryNo();
        int tickets = booking.getTickets();

        Query query = new Query(
                Criteria.where("_id").is(eventId)
                        .and("galleries.galleryNo").is(galleryNo)
                        .and("galleries.availableSeats").gte(tickets)
        );

        Update update = new Update()
                .inc("galleries.$.availableSeats", -tickets);

        Event event = mongoTemplate.findAndModify(
                query,
                update,
                FindAndModifyOptions.options().returnNew(true),
                Event.class
        );

        if (event == null) {
            throw new RuntimeException("Not enough seats available");
        }

        Gallery gallery = event.getGalleries().stream()
                .filter(g -> g.getGalleryNo().equals(galleryNo))
                .findFirst()
                .orElseThrow();

        booking.setAmount(gallery.getPrice() * tickets);
        booking.setStatus("PENDING_PAYMENT");
        booking.setBookingTime(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    // STEP 2: GET BOOKING
    public Booking getBookingById(String bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    // STEP 3: CONFIRM BOOKING AFTER PAYMENT
    public Booking confirmBooking(String bookingId) {
        Booking booking = getBookingById(bookingId);
        booking.setStatus("CONFIRMED");
        return bookingRepository.save(booking);
    }

    // STEP 4: USER BOOKINGS
    public List<Booking> getUserBookings(String userId) {
        return bookingRepository.findUserById(userId);
    }
}

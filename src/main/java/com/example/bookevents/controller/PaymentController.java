package com.example.bookevents.controller;

import com.example.bookevents.model.Booking;
import com.example.bookevents.service.BookingService;
import com.example.bookevents.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/upi/pay/{bookingId}")
    public Booking mockUpiPayment(@PathVariable String bookingId) {

        Booking booking = bookingService.getBookingById(bookingId);

        boolean success = paymentService.processMockUpiPayment(
                bookingId, booking.getAmount());

        if (!success) {
            throw new RuntimeException("Payment Failed");
        }

        return bookingService.confirmBooking(bookingId);
    }
}

package com.example.bookevents.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public boolean processMockUpiPayment(String bookingId, double amount) {
        try {
            Thread.sleep(1500); // simulate delay
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true; // always success (mock)
    }
}

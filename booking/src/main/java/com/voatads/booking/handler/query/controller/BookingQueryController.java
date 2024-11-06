package com.voatads.booking.handler.query.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.voatads.booking.handler.query.service.BookingQueryService;

@CrossOrigin
@RestController
public class BookingQueryController {

    @Autowired
    BookingQueryService bookingQueryService;

    @GetMapping("/bookings")
    public ResponseEntity<String> getBookings() {
        try {
            // bookingService.getAllBookings();
            // System.out.println(bookingService.getAllBookings());
            return ResponseEntity.status(HttpStatus.CREATED).body("Ver reservas");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e);
        }
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<String> getBooking(@RequestParam String param) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body("Ver reserva");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e);
        }
    }

}

package com.voatads.booking.handler.command.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.voatads.booking.dto.BookingDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class BookingCommandController {

    @PostMapping("/bookings")
    public ResponseEntity<String> createBooking(@RequestBody BookingDTO booking) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body("Reserva criada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e);
        }
    }

}

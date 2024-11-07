package com.voatads.booking.handler.command.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.voatads.booking.handler.command.model.BookingCommand;
import com.voatads.booking.handler.command.service.BookingCommandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class BookingCommandController {

    @Autowired
    BookingCommandService bookingCommandService;

    @PostMapping("/bookings")
    public ResponseEntity<BookingCommand> createBooking(@RequestBody BookingCommand booking) {
        try {
            BookingCommand bookingCreated = bookingCommandService.saveBooking(booking);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookingCreated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

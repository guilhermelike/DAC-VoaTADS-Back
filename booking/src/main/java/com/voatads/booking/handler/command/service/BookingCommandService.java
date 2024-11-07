package com.voatads.booking.handler.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.booking.handler.command.model.BookingCommand;
import com.voatads.booking.handler.command.repository.BookingCommandRepository;

@Service
public class BookingCommandService {

    @Autowired
    BookingCommandRepository bookingCommandRepository;
    
    public BookingCommand saveBooking(BookingCommand booking) {
        return bookingCommandRepository.save(booking); 
    }
}

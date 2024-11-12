package com.voatads.booking.handler.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.booking.handler.command.model.BookingCommand;
import com.voatads.booking.handler.command.producer.BookingCommandProducer;
import com.voatads.booking.handler.command.repository.BookingCommandRepository;

@Service
public class BookingCommandService {

    @Autowired
    BookingCommandRepository bookingCommandRepository;

    @Autowired
    BookingCommandProducer bookingCommandProducer;
    
    public BookingCommand saveBooking(BookingCommand booking) {
        BookingCommand bookingCreatd = bookingCommandRepository.save(booking);
        bookingCommandProducer.createBooking("Reserva criada");
        return bookingCreatd;
    }
}

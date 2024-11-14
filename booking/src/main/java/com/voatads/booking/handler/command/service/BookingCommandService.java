package com.voatads.booking.handler.command.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.booking.dto.BookingDTO;
import com.voatads.booking.handler.command.model.BookingCommand;
import com.voatads.booking.handler.command.producer.BookingCommandProducer;
import com.voatads.booking.handler.command.repository.BookingCommandRepository;

@Service
public class BookingCommandService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BookingCommandRepository bookingCommandRepository;

    @Autowired
    BookingCommandProducer bookingCommandProducer;
    
    public BookingCommand saveBooking(BookingDTO bookingDTO) {
        BookingCommand bookingCommand = modelMapper.map(bookingDTO, BookingCommand.class);
        BookingCommand bookingCreated = bookingCommandRepository.save(bookingCommand);
        bookingDTO.setId(bookingCreated.getId()); // Relação entre as duas tabelas
        bookingCommandProducer.createBooking(bookingDTO);
        return bookingCreated;
    }
}

package com.voatads.booking.handler.command.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.booking.dto.BookingDTO;
import com.voatads.booking.handler.command.model.BookingCommand;
import com.voatads.booking.handler.command.producer.BookingCommandProducer;
import com.voatads.booking.handler.command.repository.BookingCommandRepository;
import com.voatads.booking.utils.BookingCodeGenerator;

@Service
public class BookingCommandService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BookingCommandRepository bookingCommandRepository;

    @Autowired
    BookingCommandProducer bookingCommandProducer;

    @Autowired
    BookingCodeGenerator bookingCodeGenerator;
    
    public BookingCommand saveBooking(BookingDTO bookingDTO) {
        System.out.println("Lidar com criação de reserva");
        BookingCommand bookingCommand = modelMapper.map(bookingDTO, BookingCommand.class);
        System.out.println(bookingCommand.toString());
        BookingCommand bookingCreated = bookingCommandRepository.save(bookingCommand);
        bookingDTO.setId(bookingCreated.getId()); // Relação entre as duas tabelas
        bookingCommandProducer.createBooking(bookingDTO);
        return bookingCreated;
    }

    public BookingCommand cancelBooking(UUID id) {
        BookingCommand bookingCommand = bookingCommandRepository.findById(id).get();
        bookingCommand.setStatus("CANCELADA");
        bookingCommandRepository.save(bookingCommand);
        bookingCommandProducer.cancelBooking(id);
        return bookingCommand;
    }

    public BookingCommand checkinBooking(UUID id) {
        BookingCommand bookingCommand = bookingCommandRepository.findById(id).get();
        bookingCommand.setStatus("CONFIRMADA");
        bookingCommandRepository.save(bookingCommand);
        bookingCommandProducer.checkinBooking(id);
        return bookingCommand;
    }

}

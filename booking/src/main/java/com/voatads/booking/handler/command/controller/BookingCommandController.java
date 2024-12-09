package com.voatads.booking.handler.command.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.voatads.booking.dto.BookingDTO;
import com.voatads.booking.handler.command.model.BookingCommand;
import com.voatads.booking.handler.command.service.BookingCommandService;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class BookingCommandController {

    private static final Logger logger = LoggerFactory.getLogger(BookingCommandController.class);

    @Autowired
    BookingCommandService bookingCommandService;

    @Autowired
    private ModelMapper modelMapper;

    // Usar saga para a criação da reserva
    @PostMapping("/bookings")
    public ResponseEntity<BookingCommand> createBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            BookingCommand bookingCreated = bookingCommandService.saveBooking(bookingDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(bookingCreated);
        } catch (Exception e) {
            logger.error("Erro ao criar reserva: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/bookings/cancel/{id}")
    public ResponseEntity<BookingDTO> cancelBooking(@PathVariable UUID id) {
        try {
            BookingCommand bookingUpdated = bookingCommandService.cancelBooking(id);
            BookingDTO bookingDTO = modelMapper.map(bookingUpdated, BookingDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(bookingDTO);
        } catch (Exception e) {
            logger.error("Erro ao criar reserva: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

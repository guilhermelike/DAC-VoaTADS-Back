package com.voatads.saga.bookingController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.voatads.saga.dto.CreateBookingDTO;
import com.voatads.saga.producer.CreateBookingProducer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class CreateBookingController {

    private static final Logger logger = LoggerFactory.getLogger(CreateBookingDTO.class);

    @Autowired
    CreateBookingProducer createBookingProducer;

    @PostMapping("/bookings/create")
    public ResponseEntity<CreateBookingDTO> eventCreateBooking(@RequestBody CreateBookingDTO createBookingDTO) {
        try {
            System.out.println("Esta em controller: ");
            //createBookingProducer.deductSeatFlight(createBookingDTO);
            createBookingProducer.createBooking(createBookingDTO);
            //createBookingProducer.deductMilesCustomer(createBookingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createBookingDTO);
        } catch (Exception e) {
            logger.error("Erro ao criar reserva: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}

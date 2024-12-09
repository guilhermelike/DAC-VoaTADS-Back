package com.voatads.booking.handler.command.consumer;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.booking.dto.BookingDTO;
import com.voatads.booking.handler.command.service.BookingCommandService;
import com.voatads.booking.handler.query.consumer.BookingQueryConsumer;
import com.voatads.booking.utils.BookingCodeGenerator;

@Service
public class CreateBookingCommandConsumer {

    private static final Logger logger = LoggerFactory.getLogger(BookingQueryConsumer.class);

    @Autowired
    BookingCommandService bookingCommandService;
    
    @Autowired
    BookingCodeGenerator bookingCodeGenerator;

    @RabbitListener(queues = "create.booking.queue")
    public void createBooking(BookingDTO bookingDTO) {
        logger.info("Recebido em consumer de command: {}", bookingDTO.toString());
        // Criar reserva
        String codBooking = bookingCodeGenerator.generateCodBooking();
        logger.info("CodBooking: {}", codBooking);
        bookingDTO.setCodBooking(codBooking);
        bookingDTO.setTimeBooking(LocalDateTime.now());
        bookingDTO.setStatus("RESERVADA");
        bookingCommandService.saveBooking(bookingDTO);
    }
}

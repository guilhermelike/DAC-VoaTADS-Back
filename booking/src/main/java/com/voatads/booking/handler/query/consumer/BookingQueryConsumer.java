package com.voatads.booking.handler.query.consumer;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.booking.dto.BookingDTO;
import com.voatads.booking.handler.query.service.BookingQueryService;

@Service
public class BookingQueryConsumer {

    private static final Logger logger = LoggerFactory.getLogger(BookingQueryConsumer.class);

    @Autowired
    BookingQueryService bookingQueryService;
    
    @RabbitListener(queues = "booking.queue")
    public void createBooking(BookingDTO messageReceived) {
        logger.info("Recebido em consumer: {}", messageReceived.toString());
        bookingQueryService.saveBooking(messageReceived);
    }

    @RabbitListener(queues = "cancel.booking.queue")
    public void cancelBooking(UUID id) {
        logger.info("Recebido em consumer, cancelar reserva");
        bookingQueryService.cancelBooking(id);
    }

    @RabbitListener(queues = "checkin.booking.queue")
    public void checkinBooking(UUID id) {
        logger.info("Recebido em consumer, checkin reserva");
        bookingQueryService.checkinBooking(id);
    }
}

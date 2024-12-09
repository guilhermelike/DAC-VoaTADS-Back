package com.voatads.booking.handler.command.producer;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.booking.dto.BookingDTO;

@Service
public class BookingCommandProducer {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void createBooking(BookingDTO message) {
        rabbitTemplate.convertAndSend("booking.queue", message);
    }

    public void cancelBooking(UUID id) {
        rabbitTemplate.convertAndSend("cancel.booking.queue", id);
    }
}
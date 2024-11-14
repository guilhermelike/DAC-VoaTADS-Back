package com.voatads.booking.handler.command.producer;

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
}
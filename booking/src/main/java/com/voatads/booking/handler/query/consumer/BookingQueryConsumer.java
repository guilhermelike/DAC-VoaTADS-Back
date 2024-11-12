package com.voatads.booking.handler.query.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookingQueryConsumer {
    
    @RabbitListener(queues = "bookingQueue")
    public void createBooking(String messageReceived) {
        System.out.println(messageReceived);
    }
}

package com.voatads.flights.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.flights.dto.CreateBookingVooDTO;
import com.voatads.flights.service.VooService;

@Service
public class CreateBookingConsumer {
    
    @Autowired
    VooService vooService;

    @RabbitListener(queues = "seat.flight.queue")
    public void createBooking(CreateBookingVooDTO messageReceived) {
        System.out.println("Em flight consumer: " +  messageReceived);
        vooService.addSeat(messageReceived);
    }
}

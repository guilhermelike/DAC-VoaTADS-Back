package com.voatads.saga.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.saga.dto.CreateBookingDTO;

@Service
public class CreateBookingProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void deductSeatFlight(CreateBookingDTO createBookingDTO) {
        System.out.println("Rabbit reduzir quantidade assento de voo");
        rabbitTemplate.convertAndSend("seat.flight.queue", createBookingDTO);
    }

    public void createBooking(CreateBookingDTO createBookingDTO) {
        System.out.println("Rabbit criar reserva");
        rabbitTemplate.convertAndSend("create.booking.queue", createBookingDTO);
    }

    public void deductMilesCustomer(CreateBookingDTO createBookingDTO) {
        rabbitTemplate.convertAndSend("use.miles.customer.queue", createBookingDTO);
    }
}

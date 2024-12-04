package com.voatads.saga.config;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;


@Component
public class RabbitMQTest {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @PostConstruct
    public void testQueues() {
        System.out.println("Verificando se as filas existem...");
        System.out.println("Fila 'seat.flight.queue': " + rabbitAdmin.getQueueProperties("seat.flight.queue"));
        System.out.println("Fila 'create.booking.queue': " + rabbitAdmin.getQueueProperties("create.booking.queue"));
        System.out.println("Fila 'use.miles.customer.queue': " + rabbitAdmin.getQueueProperties("use.miles.customer.queue"));
    }
}

package com.voatads.customer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.customer.dto.CreateCustomerDTO;

@Service
public class CreateCustomerProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void createCustomer(CreateCustomerDTO createCustomerDTO) {
        System.out.println("Rabbit criar cliente");
        rabbitTemplate.convertAndSend("create.customer.queue", createCustomerDTO);
    }
}
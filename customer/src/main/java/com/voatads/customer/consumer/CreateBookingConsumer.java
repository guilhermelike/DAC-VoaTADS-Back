package com.voatads.customer.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.customer.dto.CreateBookingDTO;
import com.voatads.customer.service.CustomerService;

@Service
public class CreateBookingConsumer {
    
    @Autowired
    CustomerService customerService;

    @RabbitListener(queues = "use.miles.customer.queue")
    public void deductMilesCustomer(CreateBookingDTO messageReceived) {
        System.out.println("Em customer consumer: " +  messageReceived);
        customerService.updateMiles(messageReceived.getId(), messageReceived.getQtdMilhas(), true);
    }
}

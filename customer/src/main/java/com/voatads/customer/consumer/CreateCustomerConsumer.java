package com.voatads.customer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.customer.dto.CreateCustomerDTO;
import com.voatads.customer.service.CustomerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class CreateCustomerConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CreateCustomerConsumer.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "create.customer.queue")
    public void createCustomer(CreateCustomerDTO messageReceived) {
        logger.info("Recebido em customer consumer: {}", messageReceived);
        try {
            customerService.createCustomer(messageReceived);
            logger.info("Cliente criado com sucesso: {}", messageReceived);
            rabbitTemplate.convertAndSend("saga.customer.success.queue", messageReceived);
        } catch (Exception e) {
            logger.error("Erro ao criar cliente: {}", messageReceived, e);
        }
    }
}
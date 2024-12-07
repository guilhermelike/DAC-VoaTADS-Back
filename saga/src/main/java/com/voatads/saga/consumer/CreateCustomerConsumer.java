package com.voatads.saga.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.saga.dto.CreateCustomerDTO;
import com.voatads.saga.producer.CreateAuthProducer;

@Service
public class CreateCustomerConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CreateCustomerConsumer.class);

    @Autowired
    CreateAuthProducer createAuthProducer;

    @RabbitListener(queues = "create.customer.queue")
    public void createAuthForCustomer(CreateCustomerDTO messageReceived) {
        logger.info("Recebido em saga consumer: {}", messageReceived);
        try {
            createAuthProducer.createAuth(messageReceived);
            logger.info("Mensagem enviada para criar auth: {}", messageReceived);
        } catch (Exception e) {
            logger.error("Erro ao processar mensagem para criar auth: {}", messageReceived, e);
        }
    }
}
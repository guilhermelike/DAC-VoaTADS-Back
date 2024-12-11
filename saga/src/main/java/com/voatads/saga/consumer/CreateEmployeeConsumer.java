package com.voatads.saga.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.saga.dto.CreateEmployeeDTO;
import com.voatads.saga.producer.CreateAuthProducer;

@Service
public class CreateEmployeeConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CreateEmployeeConsumer.class);

    @Autowired
    CreateAuthProducer createAuthProducer;

    @RabbitListener(queues = "saga.employee.success.queue")
    public void createAuthForEmployee(CreateEmployeeDTO messageReceived) {
        logger.info("Recebido em saga consumer: {}", messageReceived);
        try {
            createAuthProducer.createAuthForEmployee(messageReceived);
            logger.info("Mensagem enviada para criar auth: {}", messageReceived);
        } catch (Exception e) {
            logger.error("Erro ao processar mensagem para criar auth: {}", messageReceived, e);
        }
    }
}
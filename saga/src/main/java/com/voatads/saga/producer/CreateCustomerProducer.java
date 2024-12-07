package com.voatads.saga.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.saga.dto.CreateCustomerDTO;

@Service
public class CreateCustomerProducer {

    private static final Logger logger = LoggerFactory.getLogger(CreateCustomerProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void createCustomer(CreateCustomerDTO createCustomerDTO) {
        try {
            logger.info("Enviando mensagem para criar cliente: {}", createCustomerDTO);
            rabbitTemplate.convertAndSend("create.customer.queue", createCustomerDTO);
            logger.info("Mensagem enviada com sucesso para criar cliente: {}", createCustomerDTO);
        } catch (Exception e) {
            logger.error("Erro ao enviar mensagem para criar cliente: {}", createCustomerDTO, e);
        }
    }
}
package com.voatads.saga.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.saga.dto.CreateCustomerDTO;
import com.voatads.saga.dto.CreateEmployeeDTO;

@Service
public class CreateAuthProducer {

    private static final Logger logger = LoggerFactory.getLogger(CreateAuthProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    

    public void createAuth(CreateCustomerDTO createCustomerDTO) {
        try {
            logger.info("Enviando mensagem para criar auth: {}", createCustomerDTO);
            rabbitTemplate.convertAndSend("create.auth.queue", createCustomerDTO);
            logger.info("Mensagem enviada com sucesso para criar auth: {}", createCustomerDTO);
        } catch (Exception e) {
            logger.error("Erro ao enviar mensagem para criar auth: {}", createCustomerDTO, e);
        }
    }

    public void createAuthForEmployee(CreateEmployeeDTO createEmployeeDTO) {
        try {
            logger.info("Enviando mensagem para criar auth para funcionário: {}", createEmployeeDTO);
            rabbitTemplate.convertAndSend("create.auth.queue", createEmployeeDTO);
            logger.info("Mensagem enviada com sucesso para criar auth para funcionário: {}", createEmployeeDTO);
        } catch (Exception e) {
            logger.error("Erro ao enviar mensagem para criar auth para funcionário: {}", createEmployeeDTO, e);
        }
    }
}
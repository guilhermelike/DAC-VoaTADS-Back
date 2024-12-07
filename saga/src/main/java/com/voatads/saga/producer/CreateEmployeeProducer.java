package com.voatads.saga.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.saga.dto.CreateEmployeeDTO;

@Service
public class CreateEmployeeProducer {

    private static final Logger logger = LoggerFactory.getLogger(CreateEmployeeProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void createEmployee(CreateEmployeeDTO createEmployeeDTO) {
        try {
            logger.info("Enviando mensagem para criar funcionário: {}", createEmployeeDTO);
            rabbitTemplate.convertAndSend("create.employee.queue", createEmployeeDTO);
            logger.info("Mensagem enviada com sucesso para criar funcionário: {}", createEmployeeDTO);
        } catch (Exception e) {
            logger.error("Erro ao enviar mensagem para criar funcionário: {}", createEmployeeDTO, e);
        }
    }
}
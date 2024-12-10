package com.voatads.saga.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.saga.dto.UpdateEmployeeDTO;

@Service
public class UpdateEmployeeProducer {

    private static final Logger logger = LoggerFactory.getLogger(UpdateEmployeeProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void updateEmployee(UpdateEmployeeDTO updateEmployeeDTO) {
        try {
            rabbitTemplate.convertAndSend("update.employee.queue", updateEmployeeDTO);
            logger.info("Mensagem de atualização de funcionário enviada: {}", updateEmployeeDTO);
        } catch (Exception e) {
            logger.error("Erro ao enviar mensagem de atualização de funcionário: {}", updateEmployeeDTO, e);
        }
    }
}
package com.voatads.saga.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.saga.dto.DeleteEmployeeDTO;

@Service
public class DeleteEmployeeProducer {

    private static final Logger logger = LoggerFactory.getLogger(DeleteEmployeeProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void deleteEmployee(DeleteEmployeeDTO deleteEmployeeDTO) {
        try {
            rabbitTemplate.convertAndSend("delete.employee.queue", deleteEmployeeDTO);
            logger.info("Mensagem de inativação de funcionário enviada para a fila: {}", deleteEmployeeDTO);
        } catch (Exception e) {
            logger.error("Erro ao enviar mensagem de inativação de funcionário para a fila: {}", deleteEmployeeDTO, e);
        }
    }
}
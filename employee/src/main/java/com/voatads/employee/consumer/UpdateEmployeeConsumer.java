package com.voatads.employee.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.employee.dto.UpdateEmployeeDTO;
import com.voatads.employee.service.EmployeeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

@Service
public class UpdateEmployeeConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UpdateEmployeeConsumer.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "update.employee.queue")
    public void updateEmployee(UpdateEmployeeDTO messageReceived) {
        logger.info("Recebido em employee consumer: {}", messageReceived);
        try {
            UUID employeeId = messageReceived.getId();
            employeeService.updateEmployee(employeeId, messageReceived);
            logger.info("Funcionário atualizado com sucesso: {}", messageReceived);
            rabbitTemplate.convertAndSend("saga.employee.success.queue", messageReceived);
        } catch (Exception e) {
            logger.error("Erro ao atualizar funcionário: {}", messageReceived, e);
        }
    }
}
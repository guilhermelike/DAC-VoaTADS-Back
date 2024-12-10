package com.voatads.employee.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.employee.dto.DeleteEmployeeDTO;
import com.voatads.employee.service.EmployeeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class DeleteEmployeeConsumer {

    private static final Logger logger = LoggerFactory.getLogger(DeleteEmployeeConsumer.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "delete.employee.queue")
    public void deleteEmployee(DeleteEmployeeDTO messageReceived) {
        logger.info("Recebido em employee consumer: {}", messageReceived);
        try {
            employeeService.updateEmployeeStatus(messageReceived.getId(), messageReceived.getStatus());
            logger.info("Funcionário inativado com sucesso: {}", messageReceived);
            rabbitTemplate.convertAndSend("saga.employee.success.queue", messageReceived);
        } catch (Exception e) {
            logger.error("Erro ao inativar funcionário: {}", messageReceived, e);
        }
    }
}
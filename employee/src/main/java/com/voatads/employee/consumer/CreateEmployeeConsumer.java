package com.voatads.employee.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.employee.dto.CreateEmployeeDTO;
import com.voatads.employee.service.EmployeeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class CreateEmployeeConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CreateEmployeeConsumer.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "create.employee.queue")
    public void createEmployee(CreateEmployeeDTO messageReceived) {
        logger.info("Recebido em employee consumer: {}", messageReceived);
        try {
            employeeService.createEmployee(messageReceived);
            logger.info("Funcionario criado com sucesso: {}", messageReceived);
            rabbitTemplate.convertAndSend("saga.employee.success.queue", messageReceived);
        } catch (Exception e) {
            logger.error("Erro ao criar funcionario: {}", messageReceived, e);
        }
    }
}
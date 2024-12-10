package com.voatads.saga.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.saga.dto.UpdateEmployeeDTO;
import com.voatads.saga.producer.UpdateEmployeeProducer;

@Service
public class UpdateEmployeeConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UpdateEmployeeConsumer.class);

    @Autowired
    UpdateEmployeeProducer updateEmployeeProducer;

    @RabbitListener(queues = "update.employee.queue")
    public void updateEmployee(UpdateEmployeeDTO messageReceived) {
        logger.info("Recebido em saga consumer: {}", messageReceived);
        try {
            updateEmployeeProducer.updateEmployee(messageReceived);
            logger.info("Mensagem enviada para atualizar funcionário: {}", messageReceived);
        } catch (Exception e) {
            logger.error("Erro ao processar atualização de funcionário: {}", messageReceived, e);
        }
    }
}
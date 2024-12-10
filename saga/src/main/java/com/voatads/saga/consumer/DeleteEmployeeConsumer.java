package com.voatads.saga.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.saga.dto.DeleteEmployeeDTO;
import com.voatads.saga.producer.DeleteEmployeeProducer;

@Service
public class DeleteEmployeeConsumer {

    private static final Logger logger = LoggerFactory.getLogger(DeleteEmployeeConsumer.class);

    @Autowired
    private DeleteEmployeeProducer deleteEmployeeProducer;

    @RabbitListener(queues = "delete.employee.queue")
    public void deleteEmployee(DeleteEmployeeDTO messageReceived) {
        logger.info("Recebido em saga consumer: {}", messageReceived);
        try {
            // Forward the message to the employee service
            deleteEmployeeProducer.deleteEmployee(messageReceived);
            logger.info("Mensagem encaminhada para o serviço de funcionários: {}", messageReceived);
        } catch (Exception e) {
            logger.error("Erro ao encaminhar mensagem para o serviço de funcionários: {}", messageReceived, e);
        }
    }
}
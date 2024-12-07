package com.voatads.authentication.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.authentication.dto.CreateEmployeeDTO;
import com.voatads.authentication.dto.AuthDTO;
import com.voatads.authentication.services.AuthService;
import com.voatads.authentication.security.EncryptPassword;

@Service
public class CreateEmployeeConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CreateEmployeeConsumer.class);

    @Autowired
    AuthService authService;

    @RabbitListener(queues = "create.auth.queue")
    public void createAuthForEmployee(CreateEmployeeDTO messageReceived) {
        logger.info("Recebido em auth consumer: {}", messageReceived);
        try {
            String salt = EncryptPassword.createSaltForPassword();
            String encryptedPassword = EncryptPassword.encryptPassword("1234", salt); // Senha aleatória
            AuthDTO authDTO = new AuthDTO(messageReceived.getEmail(), encryptedPassword, "2", salt, messageReceived.getId());
            authService.createLogin(authDTO);
            logger.info("Autenticação criada com sucesso");
        } catch (Exception e) {
            logger.error("Erro ao criar autenticação para o func.", e);
        }
    }
}
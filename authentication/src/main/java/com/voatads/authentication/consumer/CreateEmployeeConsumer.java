package com.voatads.authentication.consumer;

import com.voatads.authentication.dto.CreateEmployeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.authentication.dto.AuthDTO;
import com.voatads.authentication.services.AuthService;
import com.voatads.authentication.security.EncryptPassword;

@Service
public class CreateEmployeeConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CreateEmployeeConsumer.class);

    @Autowired
    AuthService authService;

    @RabbitListener(queues = "create.auth.employee.queue")
    public void createAuthForEmployee(CreateEmployeeDTO messageReceived) {
        logger.info("Recebido em auth consumer: {}", messageReceived);
        try {
            String salt = EncryptPassword.createSaltForPassword();
            logger.info("Senha recebida para cadastro: {}", messageReceived.getPassword());
            logger.info("ID recebido para cadastro: {}", messageReceived.getId());
            String encryptedPassword = EncryptPassword.encryptPassword(messageReceived.getPassword(), salt); // Utilize a senha recebida
            AuthDTO authDTO = new AuthDTO(messageReceived.getEmail(), encryptedPassword, "1", salt, messageReceived.getId());
            authService.createLogin(authDTO);
            logger.info("Autenticação criada com sucesso");
        } catch (Exception e) {
            logger.error("Erro ao criar autenticação para o cliente", e);
        }
    }
}
package com.voatads.authentication.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.authentication.dto.CreateCustomerDTO;
import com.voatads.authentication.dto.AuthDTO;
import com.voatads.authentication.services.AuthService;
import com.voatads.authentication.security.EncryptPassword;

@Service
public class CreateCustomerConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CreateCustomerConsumer.class);

    @Autowired
    AuthService authService;

    @RabbitListener(queues = "create.auth.queue")
    public void createAuthForCustomer(CreateCustomerDTO messageReceived) {
        logger.info("Recebido em auth consumer: {}", messageReceived);
        try {
            String salt = EncryptPassword.createSaltForPassword();
            logger.info("Senha recebida para cadastro: {}", messageReceived.getPassword());
            logger.info("ID recebido para cadastro: {}", messageReceived.getId());
            String encryptedPassword = EncryptPassword.encryptPassword(messageReceived.getPassword(), salt); // Utilize a senha recebida
            AuthDTO authDTO = new AuthDTO(messageReceived.getEmail(), encryptedPassword, "2", salt, messageReceived.getId());
            authService.createLogin(authDTO);
            logger.info("Autenticação criada com sucesso");
        } catch (Exception e) {
            logger.error("Erro ao criar autenticação para o cliente", e);
        }
    }
}
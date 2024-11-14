package com.voatads.authentication.listener;

import com.voatads.authentication.config.RabbitMQConfig;
import com.voatads.authentication.model.Auth;
import com.voatads.authentication.services.AuthService;
import com.voatads.authentication.security.EncryptPassword;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmployeeListener {

    @Autowired
    private AuthService authService;

    @RabbitListener(queues = RabbitMQConfig.EMPLOYEE_QUEUE)
    public void handleEmployeeCreated(String message) {
        String[] parts = message.split(",");
        UUID id = UUID.fromString(parts[0]);
        String email = parts[1];

        String salt = EncryptPassword.createSaltForPassword();
        String encryptedPassword = EncryptPassword.encryptPassword("defaultPassword", salt);

        Auth auth = new Auth(id, email, encryptedPassword, salt, UUID.randomUUID());
        auth.setIdUser(id);
        auth.setLogin(email); // Use the employee's email as the login
        auth.setPassword(encryptedPassword); // Set the encrypted password
        auth.setSalt(salt);
        auth.setType("2"); // Set the type as needed

        authService.createLogin(auth);
    }
}
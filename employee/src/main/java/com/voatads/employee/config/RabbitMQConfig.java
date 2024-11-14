package com.voatads.employee.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EMPLOYEE_QUEUE = "employeeQueue";

    @Bean
    public Queue queue() {
        return new Queue(EMPLOYEE_QUEUE, false);
    }
}
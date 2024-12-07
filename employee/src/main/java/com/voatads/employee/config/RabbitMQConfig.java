package com.voatads.employee.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EMPLOYEE_QUEUE = "create.employee.queue";

    @Bean
    public Queue createEmployeeQueue() {
        return new Queue(EMPLOYEE_QUEUE, false);
    }
}
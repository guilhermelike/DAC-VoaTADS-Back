package com.voatads.authentication.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EMPLOYEE_QUEUE = "create.employee.queue";
    public static final String CREATE_CUSTOMER_QUEUE = "create.customer.queue";
    public static final String CREATE_AUTH_QUEUE = "create.auth.queue";
    public static final String SAGA_CUSTOMER_SUCCESS_QUEUE = "saga.customer.success.queue";

    @Bean
    public Queue employeeQueue() {
        return new Queue(EMPLOYEE_QUEUE, false);
    }

    @Bean
    public Queue createCustomerQueue() {
        return new Queue(CREATE_CUSTOMER_QUEUE, false);
    }

    @Bean
    public Queue createAuthQueue() {
        return new Queue(CREATE_AUTH_QUEUE, false);
    }

    @Bean
    public Queue sagaCustomerSuccessQueue() {
        return new Queue(SAGA_CUSTOMER_SUCCESS_QUEUE, false);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
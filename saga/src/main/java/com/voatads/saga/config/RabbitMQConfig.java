package com.voatads.saga.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue createBookingTicketFlightQueue() {
        return new Queue("seat.flight.queue", false);
    }

    @Bean
    public Queue createEmployeeQueue() {
        return new Queue("create.employee.queue", false);
    }

    @Bean
    public Queue createBookingQueue() {
        return new Queue("create.booking.queue", false);
    }

    @Bean
    public Queue createBookingMilesCustomerQueue() {
        return new Queue("use.miles.customer.queue", false);
    }

    @Bean
    public Queue createCustomerQueue() {
        return new Queue("create.customer.queue", false);
    }

    @Bean
    public Queue sagaCustomerSuccessQueue() {
        return new Queue("saga.customer.success.queue", false);
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

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}
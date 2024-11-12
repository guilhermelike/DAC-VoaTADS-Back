package com.voatads.booking.handler.command.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    
    @Bean
    public Queue bookinQueue() {
        return new Queue("bookingQueue", false);
    }
}

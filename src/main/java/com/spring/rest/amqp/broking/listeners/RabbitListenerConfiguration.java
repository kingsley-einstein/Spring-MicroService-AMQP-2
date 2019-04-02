package com.spring.rest.amqp.broking.listeners;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitListenerConfiguration implements RabbitListenerConfigurer {

    @Bean
    public TopicExchange exhange(@Value("${service.exchangeKey}") String exchanger) {
        return new TopicExchange(exchanger);
    }

    @Bean
    public Queue queue(@Value("${service.queue}") String queue) {
        return new Queue(queue, true);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {

    }

}
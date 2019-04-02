package com.spring.rest.amqp.broking.listeners;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

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

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange,
            @Value("${service.profileCreatedKey}") String profileCreatedKey) {
        return BindingBuilder.bind(queue).to(exchange).with(profileCreatedKey);
    }

    @Bean
    public MappingJackson2MessageConverter converter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory factory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(converter());

        return factory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(factory());
    }

}
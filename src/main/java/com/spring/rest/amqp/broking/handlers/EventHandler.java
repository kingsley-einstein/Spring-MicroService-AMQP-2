package com.spring.rest.amqp.broking.handlers;

import com.spring.rest.amqp.broking.events.ProfileCreatedEvent;
import com.spring.rest.amqp.model.Profile;
import com.spring.rest.amqp.repositories.ProfileRepository;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventHandler {
    private ProfileRepository repository;

    public EventHandler(ProfileRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "${service.queue}")
    public void handleProfileCreated(ProfileCreatedEvent event) {
        log.info("Profile created event received: {}", event);

        try {
            repository.save(new Profile(event.getEmail(), event.getFirstName(), event.getLastName(), event.getAge(),
                    event.getOccupation()));
        } catch (Exception e) {
            log.error("Error occured while trying to process event: {} ", e);

            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
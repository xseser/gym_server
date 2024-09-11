package com.gym.mail.kafka.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.mail.kafka.dto.UserRegistration;
import com.gym.mail.kafka.service.EventToMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    private final EventToMailService eventToMailService;
    private final ObjectMapper objectMapper;

    public KafkaConsumer(EventToMailService eventToMailService, ObjectMapper objectMapper) {
        this.eventToMailService = eventToMailService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "group_id", groupId = "group_id")
    public void consume(String message) {
        log.info("Received raw message: {}", message);
        try {
            UserRegistration userRegistration = objectMapper.readValue(message, UserRegistration.class); //TODO switch to mapper to common object
            log.info("Converted object: {}", userRegistration);
            eventToMailService.processMailGenerationAndSending(userRegistration);
        } catch (Exception e) {
            log.error("Failed to parse message", e);
        }
    }
}
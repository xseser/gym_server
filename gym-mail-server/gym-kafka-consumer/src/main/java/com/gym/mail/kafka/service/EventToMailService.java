package com.gym.mail.kafka.service;

import com.gym.mail.kafka.dto.CommonMailDto;
import com.gym.mail.kafka.service.processor.EventTypeRecognizer;
import org.springframework.stereotype.Service;

@Service
public class EventToMailService {

    private final EventTypeRecognizer eventTypeRecognizer;

    public EventToMailService(EventTypeRecognizer eventTypeRecognizer) {
        this.eventTypeRecognizer = eventTypeRecognizer;
    }

    public void processMailGenerationAndSending(CommonMailDto event) {
        eventTypeRecognizer.get(event)
                .process(event);
    }
}

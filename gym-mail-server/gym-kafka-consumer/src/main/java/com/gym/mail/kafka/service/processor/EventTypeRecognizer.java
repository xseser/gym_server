package com.gym.mail.kafka.service.processor;

import com.gym.mail.kafka.dto.Advertisement;
import com.gym.mail.kafka.dto.CommonMailDto;
import com.gym.mail.kafka.dto.UserRegistration;
import com.gym.mail.kafka.factory.EventTypeFactory;
import com.gym.mail.kafka.service.processor.impl.AdvertisementProcessor;
import com.gym.mail.kafka.service.processor.impl.UserRegistrationProcessor;
import org.springframework.stereotype.Service;

@Service
public class EventTypeRecognizer {

    private final EventTypeFactory eventTypeFactory;

    public EventTypeRecognizer(EventTypeFactory eventTypeFactory) {
        this.eventTypeFactory = eventTypeFactory;
    }

    public EventMailProcessor get(CommonMailDto event) {
        return switch (event) {
            case UserRegistration e -> eventTypeFactory.get(UserRegistrationProcessor.class);
            case Advertisement e -> eventTypeFactory.get(AdvertisementProcessor.class);
            default -> null;
        };
    }
}

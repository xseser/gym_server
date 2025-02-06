package com.backend.gym.controller.proxy.config;

import com.gym.kafka.producer.sender.GymKafkaProducer;
import com.gym.kafka.producer.mail.EventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@Service
public class MockedKafkaProducer implements GymKafkaProducer {

    @Override
    public void sendMessage(EventDto eventDto, Consumer<EventDto> onSuccess, Consumer<EventDto> onError) {
        log.info("I cannot send event: {}, because I am a mock implementation", eventDto);
    }
}

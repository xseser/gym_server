package com.backend.gym.controller.proxy.config;

import com.gym.kafka.producer.GymKafkaProducer;
import com.gym.kafka.producer.mail.EventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MockedKafkaProducer implements GymKafkaProducer {

    @Override
    public void sendMessage(EventDto eventDto) {
        log.info("I cannot send event: {}, because I am a mock implementation", eventDto);
    }
}

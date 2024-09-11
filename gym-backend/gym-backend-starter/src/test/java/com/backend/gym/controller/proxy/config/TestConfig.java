package com.backend.gym.controller.proxy.config;

import com.gym.kafka.producer.GymKafkaProducer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public GymKafkaProducer kafkaProducer() {
        return new MockedKafkaProducer();
    }
}

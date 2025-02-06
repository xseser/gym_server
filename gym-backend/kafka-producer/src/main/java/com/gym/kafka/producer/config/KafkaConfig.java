package com.gym.kafka.producer.config;

import com.gym.kafka.producer.sender.GymKafkaProducer;
import com.gym.kafka.producer.sender.KafkaProducerWrapper;
import com.gym.kafka.producer.mail.EventDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    private final String kafkaMailGroup;
    private final String kafkaBootstrapServers;

    public KafkaConfig(
            @Value("${kafka.mail.group}") String kafkaMailGroup,
            @Value("${spring.kafka.bootstrap-servers}") String kafkaBootstrapServers) {
        this.kafkaMailGroup = kafkaMailGroup;
        this.kafkaBootstrapServers = kafkaBootstrapServers;
    }

    @Bean
    public ProducerFactory<String, EventDto> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ProducerConfig.RETRIES_CONFIG, 5);
        config.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 2000);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, EventDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public GymKafkaProducer kafkaProducer() {
        return new KafkaProducerWrapper(kafkaTemplate(), kafkaMailGroup);
    }
}

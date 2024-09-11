package com.gym.kafka.producer;

import com.gym.kafka.producer.mail.EventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerWrapper implements GymKafkaProducer {

    private final KafkaTemplate<String, EventDto> kafkaTemplate;
    private final String kafkaMailGroup;

    public KafkaProducerWrapper(KafkaTemplate<String, EventDto> kafkaTemplate,
            @Value("${kafka.mail.group}") String kafkaMailGroup) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaMailGroup = kafkaMailGroup;
    }

    @Override
    public void sendMessage(EventDto mailDto) {
        log.info("sending message to kafka: {}", mailDto);
        kafkaTemplate.send(kafkaMailGroup, mailDto);
    }
}

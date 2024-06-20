package com.gym.user.registration.kafka;

import com.gym.user.registration.mail.CommonMailDto;
import com.gym.user.registration.mail.RegistrationMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@Service
public class KafkaProducer {

    private final KafkaTemplate<String, CommonMailDto> kafkaTemplate;
    private final String kafkaMailGroup;

    public KafkaProducer(KafkaTemplate<String, CommonMailDto> kafkaTemplate,
            @Value("${kafka.mail.group}") String kafkaMailGroup) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaMailGroup = kafkaMailGroup;
    }

    public void sendMessage(CommonMailDto mailDto) {
        log.info("sending message to kafka producer: {}", mailDto);
        kafkaTemplate.send(kafkaMailGroup, mailDto);
    }
}

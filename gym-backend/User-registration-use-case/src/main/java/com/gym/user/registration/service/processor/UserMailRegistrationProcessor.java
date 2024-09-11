package com.gym.user.registration.service.processor;

import com.gym.kafka.producer.GymKafkaProducer;
import com.gym.kafka.producer.mail.EventDto;
import com.gym.kafka.producer.mail.UserRegistration;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMailRegistrationProcessor {

    private final GymKafkaProducer kafkaProducer;

    public UserMailRegistrationProcessor(GymKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void sendEmailRegistrationConfirmation(UserRegistrationResponseDto responseDto) {
        kafkaProducer.sendMessage(mapToMail(responseDto));
    }

    private EventDto mapToMail(UserRegistrationResponseDto userRegistrationResponseDto) {
        return new UserRegistration(
                userRegistrationResponseDto.getNickname(),
                userRegistrationResponseDto.getMail(),
                "true");
    }
}

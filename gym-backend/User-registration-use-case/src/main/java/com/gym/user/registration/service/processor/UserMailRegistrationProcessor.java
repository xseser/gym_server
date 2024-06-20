package com.gym.user.registration.service.processor;

import com.gym.user.registration.adapter.GymMailAdapter;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import com.gym.user.registration.kafka.KafkaProducer;
import com.gym.user.registration.mail.CommonMailDto;
import com.gym.user.registration.mail.UserRegistration;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
public class UserMailRegistrationProcessor {

    private final GymMailAdapter gymMailAdapter;
    private final Executor executor;
    private final KafkaProducer kafkaProducer;

    public UserMailRegistrationProcessor(GymMailAdapter gymMailAdapter, Executor executor, KafkaProducer kafkaProducer) {
        this.gymMailAdapter = gymMailAdapter;
        this.executor = executor;
        this.kafkaProducer = kafkaProducer;
    }

    public void sendEmailRegistrationConfirmation(UserRegistrationResponseDto responseDto) {
        kafkaProducer.sendMessage(mapToMail(responseDto));
    }

    private CommonMailDto mapToMail(UserRegistrationResponseDto userRegistrationResponseDto) {
        return new UserRegistration(
                userRegistrationResponseDto.getNickname(),
                userRegistrationResponseDto.getMail(),
                "true");
    }
}

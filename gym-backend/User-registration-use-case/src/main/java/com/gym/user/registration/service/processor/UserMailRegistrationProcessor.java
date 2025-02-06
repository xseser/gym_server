package com.gym.user.registration.service.processor;

import com.gym.kafka.producer.model.MailConfirmationDispatchQueue;
import com.gym.kafka.producer.model.VerificationStateToChange;
import com.gym.kafka.producer.repository.MailDispatchQueueRepository;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMailRegistrationProcessor {

    private final MailDispatchQueueRepository mailDispatchQueueRepository;

    public UserMailRegistrationProcessor(MailDispatchQueueRepository mailDispatchQueueRepository) {
        this.mailDispatchQueueRepository = mailDispatchQueueRepository;
    }

    public void persistPendingMail(UserRegistrationResponseDto responseDto, VerificationStateToChange verificationStateToChange) {
        MailConfirmationDispatchQueue mailConfirmationDispatchQueue = mapToEntity(responseDto, verificationStateToChange.verificationState());
        mailDispatchQueueRepository.save(mailConfirmationDispatchQueue);
    }

    private MailConfirmationDispatchQueue mapToEntity(UserRegistrationResponseDto responseDto, boolean isVerified) {
        return MailConfirmationDispatchQueue.builder()
                .id(UUID.randomUUID())
                .nickname(responseDto.getNickname())
                .mail(responseDto.getMail())
                .verificationState(isVerified)
                .build();
    }
}

package com.gym.mail.management.service.impl;

import com.gym.mail.generator.repository.MailConfirmationRepository;
import com.gym.mail.management.adapter.BackendAdapter;
import com.gym.mail.management.dto.ConfirmationDto;
import com.gym.mail.management.service.MailConfirmationProcessor;
import com.gym.mail.management.service.dto.UserRegisterConfirmation;
import com.response.gym.response.BadRequest;
import com.response.gym.response.MMTResponseCreator;
import com.response.gym.response.Ok;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MailConfirmationProcessorForRegistrationImpl implements MailConfirmationProcessor {

    private final BackendAdapter<UserRegisterConfirmation> backendAdapter;
    private final MailConfirmationRepository mailConfirmationRepository;

    public MailConfirmationProcessorForRegistrationImpl(BackendAdapter<UserRegisterConfirmation> backendAdapter,
            MailConfirmationRepository mailConfirmationRepository) {
        this.backendAdapter = backendAdapter;
        this.mailConfirmationRepository = mailConfirmationRepository;
    }

    @Override
    public MMTResponseCreator perform(ConfirmationDto confirmationDto) {
        ResponseEntity response =  backendAdapter.sendMailReceiveConfirmation(
                getBody(confirmationDto.getNickname()));

        if (response.getStatusCode().is2xxSuccessful()) {
            mailConfirmationRepository.deleteById(confirmationDto.getId());
            return new Ok();
        }
        return new BadRequest();
    }

    private UserRegisterConfirmation getBody(String nickname) {
        return UserRegisterConfirmation.builder()
                .isVerified(true)
                .nickname(nickname)
                .build();
    }
}

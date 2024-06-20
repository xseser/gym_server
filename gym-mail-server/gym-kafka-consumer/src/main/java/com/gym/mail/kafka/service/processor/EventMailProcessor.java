package com.gym.mail.kafka.service.processor;

import com.gym.mail.adapter.service.EmailRecognizer;
import com.gym.mail.generator.dto.MailConfirmationRequestDto;
import com.gym.mail.generator.dto.ValidMailConfirmationDto;
import com.gym.mail.generator.model.EmailType;
import com.gym.mail.generator.service.MailConfirmationService;
import com.gym.mail.kafka.dto.CommonMailDto;
import org.springframework.stereotype.Component;

@Component
public abstract class EventMailProcessor {

    protected final MailConfirmationService mailConfirmationService;
    protected final EmailRecognizer emailRecognizer;

    protected EventMailProcessor(MailConfirmationService mailConfirmationService, EmailRecognizer emailRecognizer) {
        this.mailConfirmationService = mailConfirmationService;
        this.emailRecognizer = emailRecognizer;
    }

    public abstract EmailType getEmailType();

    private ValidMailConfirmationDto generateConfirmation(CommonMailDto event) {
        return mailConfirmationService.generateNewConfirmationIfNotExist(map(event), getEmailType());
    }

    private static MailConfirmationRequestDto map(CommonMailDto event) {
        return new MailConfirmationRequestDto(event.getNickname(), event.getMail());
    }

    public void process(CommonMailDto event) {
        emailRecognizer.getEmailSenderForAction(getEmailType()).send(generateConfirmation(event));
    }

}

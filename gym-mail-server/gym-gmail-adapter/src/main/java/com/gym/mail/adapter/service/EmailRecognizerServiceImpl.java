package com.gym.mail.adapter.service;

import com.gym.mail.adapter.service.factory.EmailTypeFactory;
import com.gym.mail.adapter.service.providers.EmailAdvertisementService;
import com.gym.mail.adapter.service.providers.EmailPasswordResetService;
import com.gym.mail.adapter.service.providers.EmailRegistrationService;
import com.gym.mail.adapter.service.providers.EmailSender;
import com.gym.mail.generator.model.EmailType;
import org.springframework.stereotype.Service;

@Service
public class EmailRecognizerServiceImpl implements EmailRecognizer {

    private final EmailTypeFactory emailTypeFactory;

    public EmailRecognizerServiceImpl(EmailTypeFactory emailTypeFactory) {
        this.emailTypeFactory = emailTypeFactory;
    }

    @Override
    public EmailSender getEmailSenderForAction(EmailType emailType) {
        return switch (emailType) {
            case REGISTRATION -> emailTypeFactory.getEmailSender(EmailRegistrationService.class);
            case ADVERTISEMENT -> emailTypeFactory.getEmailSender(EmailAdvertisementService.class);
            case PASSWORD_RESET -> emailTypeFactory.getEmailSender(EmailPasswordResetService.class);
        };
    }
}

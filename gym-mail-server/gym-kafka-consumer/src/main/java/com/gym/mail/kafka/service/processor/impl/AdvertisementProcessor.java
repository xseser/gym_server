package com.gym.mail.kafka.service.processor.impl;

import com.gym.mail.adapter.service.EmailRecognizer;
import com.gym.mail.generator.model.EmailType;
import com.gym.mail.generator.service.MailConfirmationService;
import com.gym.mail.kafka.service.processor.EventMailProcessor;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementProcessor extends EventMailProcessor {

    public AdvertisementProcessor(MailConfirmationService mailConfirmationService, EmailRecognizer emailRecognizer) {
        super(mailConfirmationService, emailRecognizer);
    }

    @Override
    public EmailType getEmailType() {
        return EmailType.ADVERTISEMENT;
    }
}

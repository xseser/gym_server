package com.gym.mail.adapter.service;

import com.gym.mail.adapter.service.providers.EmailSender;
import com.gym.mail.generator.model.EmailType;

public interface EmailRecognizer {

    EmailSender getEmailSenderForAction(EmailType emailType);
}

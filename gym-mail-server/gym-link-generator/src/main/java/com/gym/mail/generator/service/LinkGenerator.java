package com.gym.mail.generator.service;

import com.gym.mail.generator.dto.MailConfirmationRequestDto;
import com.gym.mail.generator.model.EmailType;
import com.gym.mail.generator.model.MailConfirmation;

public interface LinkGenerator {

    MailConfirmation generateAndSaveNewConfirmation(MailConfirmationRequestDto mailConfirmationRequestDto, EmailType emailType);
}

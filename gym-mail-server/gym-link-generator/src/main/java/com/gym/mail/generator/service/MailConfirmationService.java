package com.gym.mail.generator.service;

import com.gym.mail.generator.dto.MailConfirmationRequestDto;
import com.gym.mail.generator.dto.ValidMailConfirmationDto;
import com.gym.mail.generator.model.EmailType;

public interface MailConfirmationService {

    ValidMailConfirmationDto generateNewConfirmationIfNotExist(MailConfirmationRequestDto mailConfirmationRequestDto, EmailType emailType);
}

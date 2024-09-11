package com.gym.mail.management.service;

import com.gym.mail.management.dto.ConfirmationDto;
import com.response.gym.response.MMTResponseCreator;

public interface MailConfirmationProcessor {

    MMTResponseCreator perform(ConfirmationDto confirmationDto);
}

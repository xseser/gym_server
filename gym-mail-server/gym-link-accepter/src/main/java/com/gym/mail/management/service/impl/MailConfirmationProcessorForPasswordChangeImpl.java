package com.gym.mail.management.service.impl;

import com.gym.mail.management.dto.ConfirmationDto;
import com.gym.mail.management.service.MailConfirmationProcessor;
import com.response.gym.response.MMTResponseCreator;
import com.response.gym.response.Ok;
import org.springframework.stereotype.Component;

@Component
public class MailConfirmationProcessorForPasswordChangeImpl implements MailConfirmationProcessor {

    @Override
    public MMTResponseCreator perform(ConfirmationDto confirmationDto) {
        return new Ok();
    }
}

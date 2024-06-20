package com.gym.mail.adapter.service.factory;

import com.gym.mail.adapter.service.providers.EmailSender;

public interface EmailTypeFactory {

    EmailSender getEmailSender(Class<? extends EmailSender> validatorClass);
}

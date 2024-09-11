package com.gym.mail.adapter.service.factory;

import com.gym.mail.adapter.service.providers.EmailSender;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailTypeFactoryImpl implements EmailTypeFactory {

    private final BeanFactory beanFactory;

    public EmailTypeFactoryImpl(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public EmailSender getEmailSender(Class<? extends EmailSender> validatorClass) {
        return beanFactory.getBean(validatorClass);
    }
}

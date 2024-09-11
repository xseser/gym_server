package com.gym.mail.management.factory;

import com.gym.mail.management.service.MailConfirmationProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

@Component
public class ActionResolverFactory {

    private final BeanFactory beanFactory;

    public ActionResolverFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public MailConfirmationProcessor getActionForType(Class<? extends MailConfirmationProcessor> validatorClass) {
        return beanFactory.getBean(validatorClass);
    }
}

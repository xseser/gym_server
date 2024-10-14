package com.gym.mail.kafka.factory;

import com.gym.mail.kafka.service.processor.EventMailProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

@Component
public class EventTypeFactory {

    private final BeanFactory beanFactory;

    public EventTypeFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public EventMailProcessor get(Class<? extends EventMailProcessor> validatorClass) {
        return beanFactory.getBean(validatorClass);
    }
}

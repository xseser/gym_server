package com.core.gym.factory;

import com.core.gym.validator.Validator;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultValidatorFactory implements ValidatorFactory {

    private final BeanFactory beanFactory;

    @Autowired
    public DefaultValidatorFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public <BASE, CORRECT> Validator<BASE, CORRECT> getValidator(Class<? extends Validator<BASE, CORRECT>> validatorClass) {
        return beanFactory.getBean(validatorClass);
    }
}
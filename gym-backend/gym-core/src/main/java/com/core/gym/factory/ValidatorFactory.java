package com.core.gym.factory;

import com.core.gym.validator.Validator;

public interface ValidatorFactory {
    <BASE, CORRECT> Validator<BASE, CORRECT> getValidator(Class<? extends Validator<BASE, CORRECT>> validatorClass);
}
package com.gym.controller.validator;

import com.gym.common.response.MMTResponseCreator;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public abstract non-sealed class Validator<BASE, CORRECT> extends BaseValidator {

    public abstract CORRECT validate(BASE f);

    public MMTResponseCreator validateAndInsertBehaviour(BASE f, Function<CORRECT, MMTResponseCreator> behaviour) {
        CORRECT correctData = validate(f);
        return behaviour.apply(correctData);
    }
}

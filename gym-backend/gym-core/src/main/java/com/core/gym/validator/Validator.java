package com.core.gym.validator;

import com.response.gym.response.MMTResponseCreator;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public abstract class Validator<BASE, CORRECT> {

    public abstract CORRECT validate(BASE f);

    public MMTResponseCreator validateAndInsertBehaviour(BASE f, Function<CORRECT, MMTResponseCreator> behaviour) {
        CORRECT correctData = validate(f);
        return behaviour.apply(correctData);
    }
}

package com.core.gym.validator;

import com.response.gym.response.MMTResponseCreator;
import cyclops.control.Either;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public abstract class Validator<BASE, CORRECT> {

    public abstract Either<MMTResponseCreator, CORRECT> validate(BASE f);

    public MMTResponseCreator validateAndInsertBehaviour(BASE f, Function<CORRECT, MMTResponseCreator> behaviour) {
        return validate(f)
                .map(behaviour)
                .mapLeft(Function.identity())
                .fold(identity -> identity, identity -> identity);
    }
}

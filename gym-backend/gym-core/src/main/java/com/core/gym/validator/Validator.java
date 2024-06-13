package com.core.gym.validator;

import com.response.gym.response.BadRequest;
import com.response.gym.response.MMTResponseCreator;
import cyclops.control.Either;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public abstract class Validator<BASE, CORRECT> {

    public abstract Either<Integer, CORRECT> validate(BASE f);

    public MMTResponseCreator validateAndInsertBehaviour(BASE f, Function<CORRECT, MMTResponseCreator> behaviour) {
        return validate(f)
                .map(behaviour)
                .mapLeft(BadRequest::new)
                .mapLeft(Function.identity())
                .fold(identity -> identity, identity -> identity);
    }
}

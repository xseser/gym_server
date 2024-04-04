package com.gym.controller.validator;

import com.gym.common.response.BadRequest;
import com.gym.common.response.MMTResponseCreator;
import com.hubspot.algebra.Result;
import cyclops.control.Either;
import org.apache.commons.lang3.tuple.Triple;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Validator<F, T> {

    public abstract Either<MMTResponseCreator, T> validate(F f);

    public MMTResponseCreator validateAndInsertBehaviour(F f, Function<T, MMTResponseCreator> behaviour) {
        return validate(f)
                .flatMap(it -> Either.left(behaviour.apply(it)))
                .getLeft()
                .orElseGet(BadRequest::new);
    }

    private Result<String, MMTResponseCreator> simpleStringValidator(String check) {
        if (check == null || check.isEmpty()) {
            return Result.err(new BadRequest());
        }
        return Result.ok(check.strip());
    }

    @SafeVarargs
    protected final void assignDataToValidRequest(Triple<Consumer<String>, String, Integer>... consumer) {
        for (Triple<Consumer<String>, String, Integer> pairs : consumer) {
            String validatedString = simpleStringValidator(pairs.getMiddle()).expect(String.valueOf(pairs.getRight()));
            pairs.getLeft().accept(validatedString);
        }
    }
}

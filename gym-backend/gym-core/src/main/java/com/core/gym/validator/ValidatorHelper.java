package com.core.gym.validator;

import cyclops.control.Either;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.response.gym.controller.answer.UserAnswers.INTERNAL_ERROR;

@Component
public class ValidatorHelper<RESULT_VALIDATION> {

    public final void validateAndApplyFunction(
            Supplier<Either<Integer, RESULT_VALIDATION>> validator,
            Consumer<RESULT_VALIDATION> insertedConsumer) {
        validator.get()
                .bipeek(
                        error -> {
                            throw new IllegalStateException(String.valueOf(error));
                        },
                        insertedConsumer);
    }

    @SafeVarargs
    public final Either<Integer, Void> validateAndApplyFunction(
            Pair<Supplier<Either<Integer, RESULT_VALIDATION>>, Consumer<RESULT_VALIDATION>>... validations) {
                for (Pair<Supplier<Either<Integer, RESULT_VALIDATION>>, Consumer<RESULT_VALIDATION>> validator : validations) {
                    Either<Integer, RESULT_VALIDATION> result = validator.getKey()
                            .get()
                            .peek(validator.getRight());
                    if(result.isLeft()) {
                        return Either.left(result.getLeft().orElse(INTERNAL_ERROR));
                    }
                }
                return Either.right(null);
    }
}

package com.core.gym.validator;

import cyclops.control.Either;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Supplier;

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
}

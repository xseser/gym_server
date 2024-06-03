package com.backend.gym.controller.validator.impl.base;

import cyclops.control.Either;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class BaseValidator<ObjectUnderValidation> {

    Consumer<ObjectUnderValidation> emptyConsumer = it -> {};

    protected ObjectUnderValidation insertValidationAndGetValidResult(Supplier<Either<Integer, ObjectUnderValidation>> validator) {
        return validator.get()
                .get()
                .toOptional()
                .orElseThrow(IllegalArgumentException::new);
    }

    protected Integer insertValidationAndGetInvalidResult(Supplier<Either<Integer, ObjectUnderValidation>> validator) {
        return validator.get()
                .getLeft()
                .toOptional()
                .orElseThrow(IllegalArgumentException::new);
    }
}

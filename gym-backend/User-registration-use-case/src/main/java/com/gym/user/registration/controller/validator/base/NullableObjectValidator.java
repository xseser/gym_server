package com.gym.user.registration.controller.validator.base;

import cyclops.control.Either;

import java.util.function.Consumer;

import static com.response.gym.controller.answer.UserAnswers.NULLABLE_STRING_OBJECT;

public abstract class NullableObjectValidator {

    public static Either<Integer, String> validate(String name) {
        if (name == null || name.isBlank()) {
            return Either.left(NULLABLE_STRING_OBJECT);
        } return Either.right(name);
    }

    public static Either<Integer, String> nullableValidator(String name, Consumer<String> applyFunction) {
        return validate(name).peek(applyFunction);
    }
}

package com.backend.gym.controller.validator.impl.base;

import com.core.gym.validator.BaseValidator;
import cyclops.control.Either;

import java.util.function.Consumer;

import static com.response.gym.controller.answer.UserAnswers.INVALID_NICKNAME_CREDENTIALS;

public abstract class NameValidator {

    private final static String REGEX = "^[a-zA-Z0-9]{6,25}$";

    public static Either<Integer, String> nameValidator(String name, Consumer<String> applyFunction) {
        return BaseValidator.validate(name, INVALID_NICKNAME_CREDENTIALS, REGEX)
                .peek(applyFunction);
    }
}

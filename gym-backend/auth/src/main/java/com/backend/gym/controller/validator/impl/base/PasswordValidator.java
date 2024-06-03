package com.backend.gym.controller.validator.impl.base;

import cyclops.control.Either;

import java.util.function.Consumer;

import static com.response.gym.controller.answer.UserAnswers.INVALID_PASSWORD_CREDENTIALS;
import static com.core.gym.validator.BaseValidator.validate;

public abstract class PasswordValidator {

    private final static String REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()-+])[A-Za-z\\d!@#$%^&*()-+]{6,25}$";

    public static Either<Integer, String> passwordValidator(String password, Consumer<String> applyFunction) {
        return validate(password, INVALID_PASSWORD_CREDENTIALS, REGEX)
                .peek(applyFunction);
    }
}

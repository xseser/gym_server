package com.backend.gym.controller.validator.impl.base;

import com.core.gym.validator.BaseValidator;
import cyclops.control.Either;

import java.util.function.Consumer;

import static com.response.gym.controller.answer.UserAnswers.INVALID_MAIL_CREDENTIALS;

public abstract class MailValidator {

    private final static String REGEX = "^(?=.*@).{5,100}$";

    public static Either<Integer, String> mailValidator(String mail, Consumer<String> applyFunction) {
        return BaseValidator.validate(mail, INVALID_MAIL_CREDENTIALS, REGEX)
                .peek(applyFunction);
    }
}

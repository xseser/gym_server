package com.gym.user.registration.controller.validator.base;

import cyclops.control.Either;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import static com.response.gym.controller.answer.UserAnswers.PASSWORDS_DOES_NOT_MATCH;

@Slf4j
public abstract class PasswordMatcherValidator {

    public static Either<Integer, Void> passwordMatcherValidator(String password, String passwordMatcher) {
        if(!Objects.equals(password, passwordMatcher)) {
            return Either.left(PASSWORDS_DOES_NOT_MATCH);
        }
        return Either.right(null);
    }
}

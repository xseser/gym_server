package com.gym.user.registration.controller.validator.base;

import cyclops.control.Either;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.gym.user.registration.controller.validator.base.PasswordMatcherValidator.passwordMatcherValidator;
import static com.response.gym.controller.answer.UserAnswers.PASSWORDS_DOES_NOT_MATCH;

public class PasswordMatcherValidatorTest extends BaseValidator<Void> {

    @Test
    public void passwordsWhichDoesNotMatchWillCausePasswordDoesNotMatchError() {
        String password = "Password123!";
        String passwordMatcher = "InvalidPassword";

        Integer errorCode = insertValidationAndGetInvalidResult(()-> passwordMatcherValidator(password, passwordMatcher));

        Assertions.assertThat(errorCode).isEqualTo(PASSWORDS_DOES_NOT_MATCH);
    }

    @Test
    public void passwordsWhichMatchWillReturnResultOnRightSide() {
        String password = "Password123!";
        String passwordMatcher = "Password123!";

        Either<Integer, Void> result =  passwordMatcherValidator(password, passwordMatcher);

        Assertions.assertThat(result.getLeft().isPresent()).isEqualTo(false);
    }
}
package com.backend.gym.controller.validator.impl.base;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
;

import static com.backend.gym.controller.validator.impl.base.PasswordValidator.passwordValidator;

public class PasswordValidatorTest extends BaseValidator<String> {

    @Test
    public void validPasswordCausesValidAssignment() {
        //given
        String insertedPassword = "Password231!";

        //when
        String password = insertValidationAndGetValidResult(()-> passwordValidator(insertedPassword, emptyConsumer));

        //then
        Assertions.assertThat(insertedPassword).isEqualTo(password);
    }
}
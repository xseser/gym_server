package com.gym.user.registration.controller.validator.base;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.gym.user.registration.controller.validator.base.NameValidator.nameValidator;
import static com.response.gym.controller.answer.UserAnswers.INVALID_NICKNAME_CREDENTIALS;

public class NameValidatorTest extends BaseValidator<String> {

    @Test
    public void validNameCausesCorrectlyAssignment() {
        String insertedName = "Bkasjdnak";

        String validName = insertValidationAndGetValidResult(()-> nameValidator(insertedName, emptyConsumer));

        Assertions.assertThat(validName).isEqualTo(insertedName);
    }

    @Test
    public void validNameWithWhiteSpacesCausesCorrectlyAssignment() {
        String insertedName = "     Bkasjdnak     ";

        String validName = insertValidationAndGetValidResult(()-> nameValidator(insertedName, emptyConsumer));

        Assertions.assertThat(validName).isEqualTo("Bkasjdnak");
    }

    @Test
    public void nullableNameWithWhiteSpacesCausesCorrectlyAssignment() {
        String insertedName = null;

        Integer errorCode = insertValidationAndGetInvalidResult(()-> nameValidator(insertedName, emptyConsumer));

        Assertions.assertThat(errorCode).isEqualTo(INVALID_NICKNAME_CREDENTIALS);
    }

    @Test
    public void emptyNameWithWhiteSpacesCausesCorrectlyAssignment() {
        String insertedName = "";

        Integer errorCode = insertValidationAndGetInvalidResult(()-> nameValidator(insertedName, emptyConsumer));

        Assertions.assertThat(errorCode).isEqualTo(INVALID_NICKNAME_CREDENTIALS);
    }

}
package com.gym.user.registration.controller.validator.base;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.gym.user.registration.controller.validator.base.NameValidator.nameValidator;

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

}
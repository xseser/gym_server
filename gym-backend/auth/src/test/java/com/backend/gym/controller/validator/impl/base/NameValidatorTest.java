package com.backend.gym.controller.validator.impl.base;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.backend.gym.controller.validator.impl.base.NameValidator.nameValidator;

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
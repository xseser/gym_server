package com.gym.user.registration.controller.validator.base;

import com.gym.user.registration.model.Gender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.gym.user.registration.controller.validator.base.GenderValidator.genderValidator;
import static com.response.gym.controller.answer.UserAnswers.INVALID_GENDER_CREDENTIALS;

public class GenderValidatorTest extends BaseValidator<Gender> {

    @Test
    public void manGenderCausesManGenderAssignment() {
        //given
        String insertedGender = "MAN";

        //when
        Gender gender = insertValidationAndGetValidResult(()-> genderValidator(insertedGender, emptyConsumer));

        //then
        Assertions.assertThat(gender).isEqualTo(Gender.MAN);
    }

    @Test
    public void womenGenderCausesWomanGenderAssignment() {
        //given
        String insertedGender = "WOMAN";

        //when
        Gender gender = insertValidationAndGetValidResult(()-> genderValidator(insertedGender, emptyConsumer));

        //then
        Assertions.assertThat(gender).isEqualTo(Gender.WOMAN);
    }

    @Test
    public void emptyGenderCausesUnknownGenderAssignment() {
        //given
        String insertedGender = "";

        //when
        Gender gender = insertValidationAndGetValidResult(()-> genderValidator(insertedGender, emptyConsumer));

        //then
        Assertions.assertThat(gender).isEqualTo(Gender.UNKNOWN);
    }

    @Test
    public void nullableGenderCausesUnknownGenderAssignment() {
        //given
        String insertedGender = null;

        //when
        Gender gender = insertValidationAndGetValidResult(()-> genderValidator(insertedGender, emptyConsumer));

        //then
        Assertions.assertThat(gender).isEqualTo(Gender.UNKNOWN);
    }

    @Test
    public void invalidGenderCausesBadRequestAndCode() {
        //given
        String insertedGender = "Invalid";

        //when
        Integer errorCode = insertValidationAndGetInvalidResult(()-> genderValidator(insertedGender, emptyConsumer));

        //then
        Assertions.assertThat(errorCode).isEqualTo(INVALID_GENDER_CREDENTIALS);
    }
}
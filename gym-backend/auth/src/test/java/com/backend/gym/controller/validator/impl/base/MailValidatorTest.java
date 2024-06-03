package com.backend.gym.controller.validator.impl.base;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
;

import static com.backend.gym.controller.validator.impl.base.MailValidator.mailValidator;
import static com.response.gym.controller.answer.UserAnswers.INVALID_MAIL_CREDENTIALS;

public class MailValidatorTest extends BaseValidator<String> {

    @Test
    public void validMailCausesCorrectMailAssignment() {
        //given
        String insertedMail = "sample.mail@gmail.com";

        //when
        String mail = insertValidationAndGetValidResult(()-> mailValidator(insertedMail, emptyConsumer));

        //then
        Assertions.assertThat(mail).isEqualTo(insertedMail);
    }

    @Test
    public void validMailWithWhiteSpacesCausesCorrectMailAssignment() {
        //given
        String insertedMail = "   sample.mail@gmail.com  ";

        //when
        String mail = insertValidationAndGetValidResult(()-> mailValidator(insertedMail, emptyConsumer));

        //then
        Assertions.assertThat(mail).isEqualTo("sample.mail@gmail.com");
    }

    @Test
    public void emptyMailCausesInvalidMailCredentialsError() {
        //given
        String insertedMail = "";

        //when
        Integer error = insertValidationAndGetInvalidResult(()-> mailValidator(insertedMail, emptyConsumer));

        //then
        Assertions.assertThat(error).isEqualTo(INVALID_MAIL_CREDENTIALS);
    }

    @Test
    public void nullableMailCausesInvalidMailCredentialsError() {
        //given
        String insertedMail = null;

        //when
        Integer error = insertValidationAndGetInvalidResult(()-> mailValidator(insertedMail, emptyConsumer));

        //then
        Assertions.assertThat(error).isEqualTo(INVALID_MAIL_CREDENTIALS);
    }

    @Test
    public void mailWithoutDotCausesInvalidMailCredentialsError() {
        //given
        String insertedMail = "sample.mail.com";

        //when
        Integer error = insertValidationAndGetInvalidResult(()-> mailValidator(insertedMail, emptyConsumer));

        //then
        Assertions.assertThat(error).isEqualTo(INVALID_MAIL_CREDENTIALS);
    }

    @Test
    public void mailOfLengthOf4CausesInvalidMailCredentialsError() {
        //given
        String insertedMail = "m@ac";

        //when
        Integer error = insertValidationAndGetInvalidResult(()-> mailValidator(insertedMail, emptyConsumer));

        //then
        Assertions.assertThat(error).isEqualTo(INVALID_MAIL_CREDENTIALS);
    }

    @Test
    public void mailOfLengthOf5CausesCorrectAssignment() {
        //given
        String insertedMail = "m@a.c";

        //when
        String mail = insertValidationAndGetValidResult(()-> mailValidator(insertedMail, emptyConsumer));

        //then
        Assertions.assertThat(mail).isEqualTo(insertedMail);
    }

    @Test
    public void mailOfLengthOf100CausesCorrectAssignment() {
        //given
        String insertedMail = "sampleMailSampleMailSampleMailSampleMailSampleMailSampleMailSampleMailSampleMail.example@example.com";

        //when
        String mail = insertValidationAndGetValidResult(()-> mailValidator(insertedMail, emptyConsumer));

        //then
        Assertions.assertThat(mail).isEqualTo(insertedMail);
    }

    @Test
    public void mailOfLengthOf101CausesCorrectAssignment() {
        //given
        String insertedMail = "sampleMailSampleMailSampleMailSampleMailSampleMailSampleMailSampleMailSampleMails.example@example.com";

        //when
        Integer error = insertValidationAndGetInvalidResult(()-> mailValidator(insertedMail, emptyConsumer));

        //then
        Assertions.assertThat(error).isEqualTo(INVALID_MAIL_CREDENTIALS);
    }
}
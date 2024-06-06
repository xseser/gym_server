package com.backend.gym.controller.validator.impl.base;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
;

import static com.backend.gym.controller.validator.impl.base.PasswordValidator.passwordValidator;
import static com.response.gym.controller.answer.UserAnswers.INVALID_PASSWORD_CREDENTIALS;

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

    @Test
    public void validPasswordWithWhiteSpacesCausesValidAssignment() {
        //given
        String insertedPassword = "    Password231!      ";

        //when
        String password = insertValidationAndGetValidResult(()-> passwordValidator(insertedPassword, emptyConsumer));

        //then
        Assertions.assertThat(password).isEqualTo("Password231!");
    }

    @Test
    public void fiveLengthOfPasswordWillProduceInvalidPasswordCredentialError() {
        //given
        String insertedPassword = "Pas1!";

        //when
        Integer error = insertValidationAndGetInvalidResult(()-> passwordValidator(insertedPassword, emptyConsumer));

        //then
        Assertions.assertThat(error).isEqualTo(INVALID_PASSWORD_CREDENTIALS);
    }

    @Test
    public void sixLengthOfPasswordWillProduceInvalidPasswordCredentialError() {
        //given
        String insertedPassword = "Pas1!!";

        //when
        String password = insertValidationAndGetValidResult(()-> passwordValidator(insertedPassword, emptyConsumer));

        //then
        Assertions.assertThat(insertedPassword).isEqualTo(password);
    }

    @Test
    public void twentyFiveLengthOfPasswordWillProduceInvalidPasswordCredentialError() {
        //given
        String insertedPassword = "PasswordPassword1234!!!!!";

        //when
        String password = insertValidationAndGetValidResult(()-> passwordValidator(insertedPassword, emptyConsumer));

        //then
        Assertions.assertThat(insertedPassword).isEqualTo(password);
    }

    @Test
    public void twentySixLengthOfPasswordWillProduceInvalidPasswordCredentialError() {
        //given
        String insertedPassword = "!PasswordPassword1234!!!!!";

        //when
        Integer error = insertValidationAndGetInvalidResult(()-> passwordValidator(insertedPassword, emptyConsumer));

        //then
        Assertions.assertThat(error).isEqualTo(INVALID_PASSWORD_CREDENTIALS);
    }
}
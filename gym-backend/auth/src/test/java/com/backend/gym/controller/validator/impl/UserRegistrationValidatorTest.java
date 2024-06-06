package com.backend.gym.controller.validator.impl;

import com.backend.gym.controller.request.dto.base.UserRegistrationDto;
import org.junit.jupiter.api.Test;
;

import static com.response.gym.controller.answer.UserAnswers.INVALID_MAIL_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_NICKNAME_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_PASSWORD_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.PASSWORDS_DOES_NOT_MATCH;

public class UserRegistrationValidatorTest extends BaseIntegrationTest implements BaseUserValidator {

    @Test
    public void validRequestIsCorrectlyMapped() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();

        //when & then
        validateValidUserRegistrationRequest(userRegistrationDto);
    }

    @Test
    public void invalidNickNameWillCauseInvalidNickNameCredentialsError() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setNickName(null);

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto);
    }

    @Test
    public void invalidPasswordWillCauseBadRequest() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setPassword(null);

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto);
    }

    @Test
    public void invalidMailWillCauseBadRequest() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setPassword(null);

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto);
    }
}
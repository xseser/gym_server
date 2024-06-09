package com.backend.gym.controller.validator.impl;

import com.backend.gym.controller.request.dto.base.UserRegistrationDto;
import org.junit.jupiter.api.Test;
;

import static com.response.gym.controller.answer.UserAnswers.INVALID_GENDER_CREDENTIALS;
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
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_NICKNAME_CREDENTIALS);
    }

    @Test
    public void invalidPasswordWillCauseInvalidPasswordCredentialsError() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setPassword(null);

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_PASSWORD_CREDENTIALS);
    }

    @Test
    public void invalidMailWillCauseInvalidMailCredentialsError() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setMail(null);

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_MAIL_CREDENTIALS);
    }

    @Test
    public void invalidGenderWillCauseInvalidGenderCredentialsError() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setGender("null");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_GENDER_CREDENTIALS);
    }

    @Test
    public void invalidNameAndGenderWillCauseInvalidNickNameCredentialsError() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setNickName(null);
        userRegistrationDto.setGender("null");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_NICKNAME_CREDENTIALS);
    }
}
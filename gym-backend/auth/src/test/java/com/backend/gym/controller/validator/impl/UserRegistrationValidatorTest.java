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
    public void nickNameWithExactLowerBundleLengthDoesNotCauseError() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setNickName("lengt6");

        //when & then
        validateValidUserRegistrationRequest(userRegistrationDto);
    }

    @Test
    public void nickNameWithExactUpperBundleLengthDoesNotCauseError() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setNickName("length25length25length25l");

        //when & then
        validateValidUserRegistrationRequest(userRegistrationDto);
    }

    @Test
    public void mailWithExactLowerBundleLengthDoesNotCauseAnyError() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setMail("12k@ok");

        //when & then
        validateValidUserRegistrationRequest(userRegistrationDto);
    }

    @Test
    public void mailWithExactUpperBundleLengthDoesNotCauseAnyError() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setMail("@upperBundleupperBundleupperBundleupperBundleupperBundleupperBundleupperBundleupperBundleupperBundle");

        //when & then
        validateValidUserRegistrationRequest(userRegistrationDto);
    }

    @Test
    public void passwordWithExactLowerLengthBundleCausesPasswordCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setPassword("Aab12!");
        userRegistrationDto.setPasswordMatcher("Aab12!");

        //when & then
        validateValidUserRegistrationRequest(userRegistrationDto);
    }

    @Test
    public void passwordWithExactUpperLengthBundleCausesPasswordCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setPassword("25isValidPasswordLength!!");
        userRegistrationDto.setPasswordMatcher("25isValidPasswordLength!!");

        //when & then
        validateValidUserRegistrationRequest(userRegistrationDto);
    }
}
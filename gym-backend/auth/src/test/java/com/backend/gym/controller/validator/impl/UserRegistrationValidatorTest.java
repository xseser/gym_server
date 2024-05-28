package com.backend.gym.controller.validator.impl;

import com.backend.gym.controller.request.dto.base.UserRegistrationDto;
import org.junit.Test;

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
    public void nullableNickNameCausesInvalidNicknameCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setNickName(null);

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_NICKNAME_CREDENTIALS);
    }

    @Test
    public void emptyNickNameCausesInvalidNicknameCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setNickName("");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_NICKNAME_CREDENTIALS);
    }

    @Test
    public void nickNameWithExceededLowerLengthCausesInvalidNicknameCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setNickName("leng5");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_NICKNAME_CREDENTIALS);
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
    public void nickNameWithExceededUpperBundleLengthCausesInvalidNicknameCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setNickName("length25length25length25l");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_NICKNAME_CREDENTIALS);
    }

    @Test
    public void nullableMailCausesInvalidMailCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setMail(null);

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_MAIL_CREDENTIALS);
    }

    @Test
    public void emptyMailCausesInvalidMailCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setMail("");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_MAIL_CREDENTIALS);
    }

    @Test
    public void mailWhichExceededLowerBundleLengthCausesInvalidMailCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setMail("2@Low");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_MAIL_CREDENTIALS);
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
    public void mailWhichExceededUpperBundleLengthCausesInvalidMailCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setMail("u@upperBundleupperBundleupperBundleupperBundleupperBundleupperBundleupperBundleupperBundleupperBundle");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_MAIL_CREDENTIALS);
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
    public void mailWithoutDotSignCausesInvalidMailCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setMail("sample.email");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_MAIL_CREDENTIALS);
    }

    @Test
    public void nullablePasswordCauseInvalidPasswordCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setPassword(null);

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_PASSWORD_CREDENTIALS);
    }

    @Test
    public void emptyPasswordCauseInvalidPasswordCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setPassword("");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_PASSWORD_CREDENTIALS);
    }

    @Test
    public void nullablePasswordMatcherCauseInvalidPasswordCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setPasswordMatcher(null);

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_PASSWORD_CREDENTIALS);
    }

    @Test
    public void emptyPasswordMatcherCauseInvalidPasswordCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setPasswordMatcher("");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_PASSWORD_CREDENTIALS);
    }

    @Test
    public void passwordWithoutSpecialCharacterCausesInvalidPasswordCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setPassword("Password123");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_PASSWORD_CREDENTIALS);
    }

    @Test
    public void passwordWithoutDigitalCausesInvalidPasswordCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setPassword("Password!");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_PASSWORD_CREDENTIALS);
    }

    @Test
    public void passwordWhichExceededLowerLengthBundleCausesPasswordCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setPassword("Ab12!");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_PASSWORD_CREDENTIALS);
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
    public void passwordWhichExceededUpperLengthBundleCausesPasswordCredentialsErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setPassword("26isInvalidPasswordLength!");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, INVALID_PASSWORD_CREDENTIALS);
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

    @Test
    public void passwordAndPasswordMatcherMismatchCausesPasswordsDoesNotMatchErrorCode() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setPassword("Pass123!!!!");
        userRegistrationDto.setPasswordMatcher("OtherPass12!!!!");

        //when & then
        validateInvalidUserRegistrationRequest(userRegistrationDto, PASSWORDS_DOES_NOT_MATCH);
    }
}
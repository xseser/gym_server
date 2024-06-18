package com.backend.gym.controller.proxy.registration;

import com.backend.gym.controller.proxy.ControllerProxyService;
import com.gym.user.registration.controller.request.base.UserRegistrationDto;
import com.response.gym.response.MMTResponseCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static com.response.gym.controller.answer.UserAnswers.GIVEN_USER_ALREADY_EXISTS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_GENDER_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_MAIL_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_NICKNAME_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_PASSWORD_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.PASSWORDS_DOES_NOT_MATCH;

public class UserManagementControllerRegistrationTest extends TestUserRegistrationDataProvider {

    @Autowired
    ControllerProxyService controllerProxyService;

    @Test
    public void validDataForRegistrationCausesValidResponse() {
        //given
        UserRegistrationDto userRegistrationDto = provideValidUserRegistrationDto();

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        assertValidRegistrationResponse(mmtResponseCreator.makeResponse(), userRegistrationDto);
    }

    @Test
    public void nullableGenderForRegistrationCausesValidResponse() {
        //given
        UserRegistrationDto userRegistrationDto = provideValidUserRegistrationDto();
        userRegistrationDto.setGender(null);

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        assertValidRegistrationResponse(mmtResponseCreator.makeResponse(), userRegistrationDto);
    }

    @Test
    public void invalidNicknameDuringRegistrationWillCauseInvalidNicknameResponse() {
        //given
        UserRegistrationDto userRegistrationDto = provideValidUserRegistrationDto();
        userRegistrationDto.setNickName("d");

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        assertInvalidRegistrationResponse(
                mmtResponseCreator.makeResponse(),
                HttpStatus.BAD_REQUEST,
                INVALID_NICKNAME_CREDENTIALS);
        checkIfGivenUserWasNotProvisioned(userRegistrationDto);
    }

    @Test
    public void invalidMailDuringRegistrationWillCauseInvalidNicknameResponse() {
        //given
        UserRegistrationDto userRegistrationDto = provideValidUserRegistrationDto();
        userRegistrationDto.setMail("d");

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        assertInvalidRegistrationResponse(
                mmtResponseCreator.makeResponse(),
                HttpStatus.BAD_REQUEST,
                INVALID_MAIL_CREDENTIALS);
        checkIfGivenUserWasNotProvisioned(userRegistrationDto);
    }

    @Test
    public void invalidPasswordDuringRegistrationWillCauseInvalidNicknameResponse() {
        //given
        UserRegistrationDto userRegistrationDto = provideValidUserRegistrationDto();
        userRegistrationDto.setPassword("d");
        userRegistrationDto.setPasswordMatcher("d");

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        assertInvalidRegistrationResponse(
                mmtResponseCreator.makeResponse(),
                HttpStatus.BAD_REQUEST,
                INVALID_PASSWORD_CREDENTIALS);
        checkIfGivenUserWasNotProvisioned(userRegistrationDto);
    }

    @Test
    public void invalidGenderDuringRegistrationWillCauseInvalidNicknameResponse() {
        //given
        UserRegistrationDto userRegistrationDto = provideValidUserRegistrationDto();
        userRegistrationDto.setGender("d");

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        assertInvalidRegistrationResponse(
                mmtResponseCreator.makeResponse(),
                HttpStatus.BAD_REQUEST,
                INVALID_GENDER_CREDENTIALS);
        checkIfGivenUserWasNotProvisioned(userRegistrationDto);
    }

    @Test
    public void passwordWhichDoesNotMatchDuringRegistrationWillCauseInvalidNicknameResponse() {
        //given
        UserRegistrationDto userRegistrationDto = provideValidUserRegistrationDto();
        userRegistrationDto.setPassword("InvalidPass12!");

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        assertInvalidRegistrationResponse(
                mmtResponseCreator.makeResponse(),
                HttpStatus.BAD_REQUEST,
                PASSWORDS_DOES_NOT_MATCH);
        checkIfGivenUserWasNotProvisioned(userRegistrationDto);
    }

    @Test
    public void registeringUserWithTheSameValidDataTwiceWillCauseGivenUserAlreadyExistError() {
        //given
        UserRegistrationDto userRegistrationDto = provideValidUserRegistrationDto();

        //when
        MMTResponseCreator validResponse = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        assertValidRegistrationResponse(validResponse.makeResponse(), userRegistrationDto);

        //when
        MMTResponseCreator invalidResponse = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        assertInvalidRegistrationResponse(
                invalidResponse.makeResponse(),
                HttpStatus.CONFLICT,
                GIVEN_USER_ALREADY_EXISTS);
    }
}
package com.backend.gym.controller.proxy.registration;

import com.backend.gym.controller.proxy.BaseIntegrationTest;
import com.gym.user.registration.controller.request.base.UserRegistrationDto;
import com.response.gym.response.MMTResponseCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.response.gym.controller.answer.UserAnswers.GIVEN_USER_ALREADY_EXISTS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_GENDER_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_MAIL_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_NICKNAME_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_PASSWORD_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.PASSWORDS_DOES_NOT_MATCH;

public class UserManagementControllerRegistrationTest extends BaseIntegrationTest {

    private TestUserRegistrationDataProvider data;

    @BeforeEach
    public void setUp() {
        data = new TestUserRegistrationDataProvider();
    }

    @Test
    public void validDataForRegistrationCausesValidResponse() {
        //given
        UserRegistrationDto userRegistrationDto = data.provideValidUserRegistrationDto();

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        data.assertValidRegistrationResponse(mmtResponseCreator.makeResponse());
    }

    @Test
    public void nullableGenderForRegistrationCausesValidResponse() {
        //given
        UserRegistrationDto userRegistrationDto = data.provideValidUserRegistrationDto();
        data.setGender(null);
        userRegistrationDto.setGender(null);

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        data.assertValidRegistrationResponse(mmtResponseCreator.makeResponse());
    }

    @Test
    public void invalidNicknameDuringRegistrationWillCauseInvalidNicknameResponse() {
        //given
        UserRegistrationDto userRegistrationDto = data.provideValidUserRegistrationDto();
        userRegistrationDto.setNickName("d");

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        data.assertInvalidRegistrationResponse(
                mmtResponseCreator.makeResponse(),
                HttpStatus.BAD_REQUEST,
                INVALID_NICKNAME_CREDENTIALS);
    }

    @Test
    public void invalidMailDuringRegistrationWillCauseInvalidNicknameResponse() {
        //given
        UserRegistrationDto userRegistrationDto = data.provideValidUserRegistrationDto();
        userRegistrationDto.setMail("d");

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        data.assertInvalidRegistrationResponse(
                mmtResponseCreator.makeResponse(),
                HttpStatus.BAD_REQUEST,
                INVALID_MAIL_CREDENTIALS);
    }

    @Test
    public void invalidPasswordDuringRegistrationWillCauseInvalidNicknameResponse() {
        //given
        UserRegistrationDto userRegistrationDto = data.provideValidUserRegistrationDto();
        userRegistrationDto.setPassword("d");
        userRegistrationDto.setPasswordMatcher("d");

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        data.assertInvalidRegistrationResponse(
                mmtResponseCreator.makeResponse(),
                HttpStatus.BAD_REQUEST,
                INVALID_PASSWORD_CREDENTIALS);
    }

    @Test
    public void invalidGenderDuringRegistrationWillCauseInvalidNicknameResponse() {
        //given
        UserRegistrationDto userRegistrationDto = data.provideValidUserRegistrationDto();
        userRegistrationDto.setGender("d");

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        data.assertInvalidRegistrationResponse(
                mmtResponseCreator.makeResponse(),
                HttpStatus.BAD_REQUEST,
                INVALID_GENDER_CREDENTIALS);
    }

    @Test
    public void passwordWhichDoesNotMatchDuringRegistrationWillCauseInvalidNicknameResponse() {
        //given
        UserRegistrationDto userRegistrationDto = data.provideValidUserRegistrationDto();
        userRegistrationDto.setPassword("InvalidPass12!");

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        data.assertInvalidRegistrationResponse(
                mmtResponseCreator.makeResponse(),
                HttpStatus.BAD_REQUEST,
                PASSWORDS_DOES_NOT_MATCH);
    }

    @Test
    public void registeringUserWithTheSameValidDataTwiceWillCauseGivenUserAlreadyExistError() {
        //given
        UserRegistrationDto userRegistrationDto = data.provideValidUserRegistrationDto();

        //when
        MMTResponseCreator validResponse = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        data.assertValidRegistrationResponse(validResponse.makeResponse());

        //when
        MMTResponseCreator invalidResponse = controllerProxyService.createUserAccount(userRegistrationDto);

        //then
        data.assertInvalidRegistrationResponse(
                invalidResponse.makeResponse(),
                HttpStatus.CONFLICT,
                GIVEN_USER_ALREADY_EXISTS);
    }
}

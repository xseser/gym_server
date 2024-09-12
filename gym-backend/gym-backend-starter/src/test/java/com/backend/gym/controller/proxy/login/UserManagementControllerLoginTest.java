package com.backend.gym.controller.proxy.login;

import com.gym.user.registration.controller.request.base.UserLoginDto;
import com.response.gym.response.MMTResponseCreator;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.response.gym.controller.answer.UserAnswers.INVALID_LOCK_STATE;
import static com.response.gym.controller.answer.UserAnswers.INVALID_NICKNAME_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_PASSWORD_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_VERIFICATION_STATE;

public class UserManagementControllerLoginTest extends TestUserLoginDataProvider {

    @Test
    public void validUserLoginRequestWillCauseTokenAndDataReturn() {
        //given
        UserLoginDto userLoginDto = provideValidUserLoginData();
        saveUserAccount();

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        assertValidUserLogin(mmtResponseCreator.makeResponse());
    }

    @Test
    public void userWhichDoesNotExistDuringLoggingWillCauseUnauthorisedError() {
        //given
        UserLoginDto userLoginDto = provideValidUserLoginData();

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        assertInvalidUserLogin(mmtResponseCreator.makeResponse(), HttpStatus.FORBIDDEN);
    }

    @Test
    public void loggingWithoutNicknameWillCauseInvalidMailCredentialsError() {
        //given
        UserLoginDto userLoginDto = provideValidUserLoginData();
        userLoginDto.setNickname(null);

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        assertInvalidUserLogin(mmtResponseCreator.makeResponse(), HttpStatus.BAD_REQUEST, INVALID_NICKNAME_CREDENTIALS);
    }

    @Test
    public void loggingWithoutPasswordWillCauseInvalidPasswordCredentialsError() {
        //given
        UserLoginDto userLoginDto = provideValidUserLoginData();
        userLoginDto.setPassword(null);

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        assertInvalidUserLogin(mmtResponseCreator.makeResponse(), HttpStatus.BAD_REQUEST, INVALID_PASSWORD_CREDENTIALS);
    }

    @Test
    public void invalidPasswordWillCauseUnauthorisedError() {
        UserLoginDto userLoginDto = provideValidUserLoginData();
        userLoginDto.setPassword("IamInvalid123^^");
        saveUserAccount();

        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        assertInvalidUserLogin(mmtResponseCreator.makeResponse(), HttpStatus.FORBIDDEN);
    }

    @Test
    public void unverifiedUserWillCauseUnauthorisedError() {
        //given
        UserLoginDto userLoginDto = provideValidUserLoginData();
        saveUserAccountWhichIsUnverified();

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        assertInvalidUserLogin(mmtResponseCreator.makeResponse(), HttpStatus.UNAUTHORIZED, INVALID_VERIFICATION_STATE);
    }

    @Test
    public void lockedUserWillCauseUnauthorisedError() {
        //given
        UserLoginDto userLoginDto = provideValidUserLoginData();
        saveUserAccountWhichIsLocked();

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        assertInvalidUserLogin(mmtResponseCreator.makeResponse(), HttpStatus.UNAUTHORIZED, INVALID_LOCK_STATE);
    }

    //TODO add test with expired user role.
}

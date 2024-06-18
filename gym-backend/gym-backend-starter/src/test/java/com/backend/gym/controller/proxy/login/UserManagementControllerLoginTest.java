package com.backend.gym.controller.proxy.login;

import com.backend.gym.controller.proxy.ControllerProxyService;
import com.gym.user.registration.controller.request.base.UserLoginDto;
import com.gym.user.registration.model.User;
import com.gym.user.registration.repository.UserRepository;
import com.response.gym.response.MMTResponseCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static com.response.gym.controller.answer.UserAnswers.GIVEN_USER_WAS_NOT_FOUND;
import static com.response.gym.controller.answer.UserAnswers.INVALID_MAIL_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_PASSWORD_CREDENTIALS;

public class UserManagementControllerLoginTest extends TestUserLoginDataProvider {

    @Autowired
    ControllerProxyService controllerProxyService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void validUserLoginRequestWillCauseTokenAndDataReturn() {
        //given
        UserLoginDto userLoginDto = provideValidUserLoginData();
        User user = mapUserLoginDtoToUser(userLoginDto);
        userRepository.save(user);

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        assertValidUserLogin(mmtResponseCreator.makeResponse());
    }

    @Test
    public void userWhichDoesNotExistDuringLoggingWillCauseGivenUserNotFoundError() {
        //given
        UserLoginDto userLoginDto = provideValidUserLoginData();

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        assertInvalidUserLogin(mmtResponseCreator.makeResponse(), HttpStatus.NOT_FOUND, GIVEN_USER_WAS_NOT_FOUND);
    }

    @Test
    public void loggingWithoutMailWillCauseInvalidMailCredentialsError() {
        //given
        UserLoginDto userLoginDto = provideValidUserLoginData();
        userLoginDto.setMail(null);

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        assertInvalidUserLogin(mmtResponseCreator.makeResponse(), HttpStatus.BAD_REQUEST, INVALID_MAIL_CREDENTIALS);
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
}

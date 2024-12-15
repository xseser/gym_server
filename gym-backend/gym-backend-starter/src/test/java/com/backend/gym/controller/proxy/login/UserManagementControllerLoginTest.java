package com.backend.gym.controller.proxy.login;

import com.backend.gym.controller.proxy.BaseIntegrationTest;
import com.gym.user.registration.controller.request.base.UserLoginDto;
import com.gym.user.registration.model.User;
import com.gym.user.registration.repository.UserRepository;
import com.response.gym.response.MMTResponseCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.function.Function;

import static com.response.gym.controller.answer.UserAnswers.INVALID_LOCK_STATE;
import static com.response.gym.controller.answer.UserAnswers.INVALID_NICKNAME_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_PASSWORD_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_VERIFICATION_STATE;

public class UserManagementControllerLoginTest extends BaseIntegrationTest {

    private TestUserLoginDataProvider testData;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @BeforeEach
    public void beforeEach() {
        this.testData = new TestUserLoginDataProvider();
    }

    @Test
    public void validUserLoginRequestWillCauseTokenAndDataReturn() {
        //given
        UserLoginDto userLoginDto = testData.provideValidUserLoginData();
        testData.saveUserAccount(encodePassword(), this::saveUserFunction);

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        testData.assertValidUserLogin(mmtResponseCreator.makeResponse());
    }

    @Test
    public void userWhichDoesNotExistDuringLoggingWillCauseUnauthorisedError() {
        //given
        UserLoginDto userLoginDto = testData.provideValidUserLoginData();

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        testData.assertInvalidUserLogin(mmtResponseCreator.makeResponse(), HttpStatus.FORBIDDEN);
    }

    @Test
    public void loggingWithoutNicknameWillCauseInvalidMailCredentialsError() {
        //given
        UserLoginDto userLoginDto = testData.provideValidUserLoginData();
        userLoginDto.setNickname(null);

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        testData.assertInvalidUserLogin(mmtResponseCreator.makeResponse(), HttpStatus.BAD_REQUEST, INVALID_NICKNAME_CREDENTIALS);
    }

    @Test
    public void loggingWithoutPasswordWillCauseInvalidPasswordCredentialsError() {
        //given
        UserLoginDto userLoginDto = testData.provideValidUserLoginData();
        userLoginDto.setPassword(null);

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        testData.assertInvalidUserLogin(mmtResponseCreator.makeResponse(), HttpStatus.BAD_REQUEST, INVALID_PASSWORD_CREDENTIALS);
    }

    @Test
    public void invalidPasswordWillCauseUnauthorisedError() {
        UserLoginDto userLoginDto = testData.provideValidUserLoginData();
        userLoginDto.setPassword("IamInvalid123^^");
        testData.saveUserAccount(encodePassword(), this::saveUserFunction);

        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        testData.assertInvalidUserLogin(mmtResponseCreator.makeResponse(), HttpStatus.FORBIDDEN);
    }

    @Test
    public void unverifiedUserWillCauseUnauthorisedError() {
        //given
        UserLoginDto userLoginDto = testData.provideValidUserLoginData();
        testData.saveUserAccount(
                encodePassword(), user -> {
                    user.setIsVerified(false);
                    return user;
                },
                this::saveUserFunction);

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        testData.assertInvalidUserLogin(mmtResponseCreator.makeResponse(), HttpStatus.UNAUTHORIZED, INVALID_VERIFICATION_STATE);
    }

    @Test
    public void lockedUserWillCauseUnauthorisedError() {
        //given
        UserLoginDto userLoginDto = testData.provideValidUserLoginData();

        testData.saveUserAccount(
                encodePassword(), user -> {
                    user.setIsLocked(true);
                    return user;
                },
                this::saveUserFunction);

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.loginAccount(userLoginDto);

        //then
        testData.assertInvalidUserLogin(mmtResponseCreator.makeResponse(), HttpStatus.UNAUTHORIZED, INVALID_LOCK_STATE);
    }

    //TODO add test with expired user role.

    private Function<String, String> encodePassword() {
        return password -> passwordEncoder.encode(password);
    }

    private void saveUserFunction(User user) {
        userRepository.save(user);
    }
}

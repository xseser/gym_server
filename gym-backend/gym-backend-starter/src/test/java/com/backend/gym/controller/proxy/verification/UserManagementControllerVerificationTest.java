package com.backend.gym.controller.proxy.verification;

import com.backend.gym.controller.proxy.BaseIntegrationTest;
import com.gym.user.registration.controller.request.base.UserRegisterConfirmation;
import com.gym.user.registration.model.User;
import com.gym.user.registration.repository.UserRepository;
import com.response.gym.response.MMTResponseCreator;
import com.response.gym.response.types.CommonResponse;
import com.response.gym.response.types.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.function.Consumer;
import java.util.function.Function;

import static com.response.gym.controller.answer.UserAnswers.GIVEN_USER_VERIFICATION_STATE_IS_ALREADY_SET;
import static com.response.gym.controller.answer.UserAnswers.GIVEN_USER_WAS_NOT_FOUND;
import static com.response.gym.controller.answer.UserAnswers.INVALID_NICKNAME_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_VERIFICATION_FLAG;

public class UserManagementControllerVerificationTest extends BaseIntegrationTest {

    private TestVerificationUserProvider testVerificationUserProvider;

    @BeforeEach
    public void setUp() {
        testVerificationUserProvider = new TestVerificationUserProvider();
    }

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userVerification_changingVerificationStateToTrue_willCauseOkStatusCode() {
        //given
        UserRegisterConfirmation userRegisterConfirmation = testVerificationUserProvider.getUserRegisterConfirmation(true);
        testVerificationUserProvider.saveUserAccount(saveUserFunction());

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.verifyUser(userRegisterConfirmation);

        //then
        testVerificationUserProvider.checkStatusCode(mmtResponseCreator.makeResponse(), HttpStatus.OK);
        testVerificationUserProvider.checkIfUserVerificationStateIsCorrect(findUser(), true);
        testVerificationUserProvider.checkResponseBody(
                mmtResponseCreator.makeResponse(),
                testVerificationUserProvider.getExpectedResponse(true));
    }

    @Test
    public void userVerification_changingVerificationStateToFalse_willCauseOkStatusCode() {
        //given
        testVerificationUserProvider.setVerified(true);
        UserRegisterConfirmation userRegisterConfirmation = testVerificationUserProvider.getUserRegisterConfirmation(false);
        testVerificationUserProvider.saveUserAccount(saveUserFunction());

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.verifyUser(userRegisterConfirmation);

        //then
        testVerificationUserProvider.checkStatusCode(mmtResponseCreator.makeResponse(), HttpStatus.OK);
        testVerificationUserProvider.checkIfUserVerificationStateIsCorrect(findUser(), false);
        testVerificationUserProvider.checkResponseBody(
                mmtResponseCreator.makeResponse(),
                testVerificationUserProvider.getExpectedResponse(false));
    }

    @Test
    public void userVerification_nullableNickname_willCauseBadRequestError() {
        //given
        UserRegisterConfirmation userRegisterConfirmation = testVerificationUserProvider.getUserRegisterConfirmation(true);
        userRegisterConfirmation.setNickname(null);
        testVerificationUserProvider.saveUserAccount(saveUserFunction());

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.verifyUser(userRegisterConfirmation);

        //then
        testVerificationUserProvider.checkStatusCode(mmtResponseCreator.makeResponse(), HttpStatus.BAD_REQUEST);
        testVerificationUserProvider.checkIfUserVerificationStateIsCorrect(findUser(), false);
        testVerificationUserProvider.checkCode(mmtResponseCreator.makeResponse(), new ErrorResponse(INVALID_NICKNAME_CREDENTIALS));
    }

    @Test
    public void userVerification_userWithGivenNickname_willCauseBadRequestError() {
        //given
        UserRegisterConfirmation userRegisterConfirmation = testVerificationUserProvider.getUserRegisterConfirmation("dAaddsd",true);
        testVerificationUserProvider.saveUserAccount(saveUserFunction());

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.verifyUser(userRegisterConfirmation);

        //then
        testVerificationUserProvider.checkStatusCode(mmtResponseCreator.makeResponse(), HttpStatus.NOT_FOUND);
        testVerificationUserProvider.checkIfUserVerificationStateIsCorrect(findUser(), false);
        testVerificationUserProvider.checkCode(mmtResponseCreator.makeResponse(), new ErrorResponse(GIVEN_USER_WAS_NOT_FOUND));
    }

    @Test
    public void userVerification_nullableVerifiedFlag_willCauseBadRequestError() {
        //given
        UserRegisterConfirmation userRegisterConfirmation = testVerificationUserProvider.getUserRegisterConfirmation(true);
        userRegisterConfirmation.setIsVerified(null);
        testVerificationUserProvider.saveUserAccount(saveUserFunction());

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.verifyUser(userRegisterConfirmation);

        //then
        testVerificationUserProvider.checkStatusCode(mmtResponseCreator.makeResponse(), HttpStatus.BAD_REQUEST);
        testVerificationUserProvider.checkIfUserVerificationStateIsCorrect(findUser(), false);
        testVerificationUserProvider.checkCode(mmtResponseCreator.makeResponse(), new ErrorResponse(INVALID_VERIFICATION_FLAG));
    }

    @Test
    public void userVerification_userIsAlreadyVerified_willCauseErrorThatUserIsAlreadyVerified() {
        //given
        UserRegisterConfirmation userRegisterConfirmation = testVerificationUserProvider.getUserRegisterConfirmation(true);
        testVerificationUserProvider.setVerified(true);
        testVerificationUserProvider.saveUserAccount(saveUserFunction());

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.verifyUser(userRegisterConfirmation);

        //then
        testVerificationUserProvider.checkStatusCode(mmtResponseCreator.makeResponse(), HttpStatus.ACCEPTED);
        testVerificationUserProvider.checkIfUserVerificationStateIsCorrect(findUser(), true);
        testVerificationUserProvider.checkCode(mmtResponseCreator.makeResponse(), new CommonResponse(GIVEN_USER_VERIFICATION_STATE_IS_ALREADY_SET));
    }

    @Test
    public void userVerification_userIsAlreadyVerified_willCauseErrorThatUserIsAlreadyUnVerified() {
        //given
        UserRegisterConfirmation userRegisterConfirmation = testVerificationUserProvider.getUserRegisterConfirmation(false);
        testVerificationUserProvider.setVerified(false);
        testVerificationUserProvider.saveUserAccount(saveUserFunction());

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.verifyUser(userRegisterConfirmation);

        //then
        testVerificationUserProvider.checkStatusCode(mmtResponseCreator.makeResponse(), HttpStatus.ACCEPTED);
        testVerificationUserProvider.checkIfUserVerificationStateIsCorrect(findUser(), false);
        testVerificationUserProvider.checkCode(mmtResponseCreator.makeResponse(), new CommonResponse(GIVEN_USER_VERIFICATION_STATE_IS_ALREADY_SET));
    }

    private Consumer<User> saveUserFunction() {
        return user -> userRepository.save(user);
    }

    private Function<String, User> findUser() {
        return userName -> userRepository.findByNickname(userName)
                .orElse(null);
    }
}

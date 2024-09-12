package com.backend.gym.controller.proxy.verification;

import com.gym.user.registration.controller.request.base.UserRegisterConfirmation;
import com.gym.user.registration.controller.response.UserVerificationResponseDto;
import com.response.gym.response.MMTResponseCreator;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.response.gym.controller.answer.UserAnswers.GIVEN_USER_VERIFICATION_STATE_IS_ALREADY_SET;
import static com.response.gym.controller.answer.UserAnswers.GIVEN_USER_WAS_NOT_FOUND;
import static com.response.gym.controller.answer.UserAnswers.INVALID_NICKNAME_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_VERIFICATION_FLAG;

public class UserManagementControllerVerificationTest extends TestVerificationUserProvider {

    @Test
    public void userVerification_changingVerificationStateToTrue_willCauseOkStatusCode() {
        //given
        UserRegisterConfirmation userRegisterConfirmation = getUserRegisterConfirmation();
        saveUserAccount();

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.verifyUser(userRegisterConfirmation);

        //then
        UserVerificationResponseDto userVerificationResponseDto = getExpectedResponse(true);
        assertUserVerification(mmtResponseCreator.makeResponse(), HttpStatus.OK, true, userVerificationResponseDto);
    }

    @Test
    public void userVerification_changingVerificationStateToFalse_willCauseOkStatusCode() {
        //given
        UserRegisterConfirmation userRegisterConfirmation = getUserRegisterConfirmation();
        userRegisterConfirmation.setIsVerified(false);
        saveUserAccount(true);

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.verifyUser(userRegisterConfirmation);

        //then
        UserVerificationResponseDto userVerificationResponseDto = getExpectedResponse(false);
        assertUserVerification(mmtResponseCreator.makeResponse(), HttpStatus.OK, false, userVerificationResponseDto);
    }

    @Test
    public void userVerification_nullableNickname_willCauseBadRequestError() {
        //given
        UserRegisterConfirmation userRegisterConfirmation = getUserRegisterConfirmation();
        userRegisterConfirmation.setNickname(null);
        saveUserAccount();

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.verifyUser(userRegisterConfirmation);

        //then
        assertUserVerification(mmtResponseCreator.makeResponse(), HttpStatus.BAD_REQUEST, false, INVALID_NICKNAME_CREDENTIALS);
    }

    @Test
    public void userVerification_userWithGivenNickname_willCauseBadRequestError() {
        //given
        UserRegisterConfirmation userRegisterConfirmation = getUserRegisterConfirmation();
        userRegisterConfirmation.setNickname("UserWhichDoesNotExist");
        saveUserAccount();

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.verifyUser(userRegisterConfirmation);

        //then
        assertUserVerification(mmtResponseCreator.makeResponse(), HttpStatus.NOT_FOUND, false, GIVEN_USER_WAS_NOT_FOUND);
    }

    @Test
    public void userVerification_nullableVerifiedFlag_willCauseBadRequestError() {
        //given
        UserRegisterConfirmation userRegisterConfirmation = getUserRegisterConfirmation();
        userRegisterConfirmation.setIsVerified(null);
        saveUserAccount();

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.verifyUser(userRegisterConfirmation);

        //then
        assertUserVerification(mmtResponseCreator.makeResponse(), HttpStatus.BAD_REQUEST, false, INVALID_VERIFICATION_FLAG);
    }

    @Test
    public void userVerification_userIsAlreadyVerified_willCauseErrorThatUserIsAlreadyVerified() {
        //given
        UserRegisterConfirmation userRegisterConfirmation = getUserRegisterConfirmation();
        saveUserAccount(true);

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.verifyUser(userRegisterConfirmation);

        //then
        assertUserVerification(mmtResponseCreator.makeResponse(), HttpStatus.ACCEPTED, true, GIVEN_USER_VERIFICATION_STATE_IS_ALREADY_SET);
    }

    @Test
    public void userVerification_userIsAlreadyVerified_willCauseErrorThatUserIsAlreadyUnVerified() {
        //given
        UserRegisterConfirmation userRegisterConfirmation = getUserRegisterConfirmation();
        userRegisterConfirmation.setIsVerified(false);
        saveUserAccount(false);

        //when
        MMTResponseCreator mmtResponseCreator = controllerProxyService.verifyUser(userRegisterConfirmation);

        //then
        assertUserVerification(mmtResponseCreator.makeResponse(), HttpStatus.ACCEPTED, false, GIVEN_USER_VERIFICATION_STATE_IS_ALREADY_SET);
    }
}

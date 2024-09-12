package com.gym.user.registration.service.processor;

import com.gym.user.registration.controller.request.valid.ValidUserLoginRequest;
import com.gym.user.registration.controller.request.valid.ValidUserRegisterConfirmation;
import com.gym.user.registration.controller.request.valid.ValidUserRegistrationRequest;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import com.gym.user.registration.model.User;
import com.gym.user.registration.repository.UserRepository;
import com.gym.user.registration.service.UserAuthManagement;
import com.response.gym.response.Accepted;
import com.response.gym.response.Conflict;
import com.response.gym.response.Created;
import com.response.gym.response.Forbidden;
import com.response.gym.response.InternalServerError;
import com.response.gym.response.MMTResponseCreator;
import com.response.gym.response.NotFound;
import com.response.gym.response.Ok;
import com.response.gym.response.Unauthorised;
import cyclops.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import static com.response.gym.controller.answer.UserAnswers.GIVEN_USER_ALREADY_EXISTS;
import static com.response.gym.controller.answer.UserAnswers.GIVEN_USER_VERIFICATION_STATE_IS_ALREADY_SET;
import static com.response.gym.controller.answer.UserAnswers.GIVEN_USER_WAS_NOT_FOUND;
import static com.response.gym.controller.answer.UserAnswers.INVALID_LOCK_STATE;
import static com.response.gym.controller.answer.UserAnswers.INVALID_LOGIN_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_VERIFICATION_STATE;

@Component
@Slf4j
public class UserAuthProcessor {

    private final UserRepository userRepository;
    private final UserAuthManagement userAuthManagement;
    private final UserMailRegistrationProcessor userMailRegistrationProcessor;

    public UserAuthProcessor(
            UserRepository userRepository,
            UserAuthManagement userAuthManagement, UserMailRegistrationProcessor userMailRegistrationProcessor) {
        this.userRepository = userRepository;
        this.userAuthManagement = userAuthManagement;
        this.userMailRegistrationProcessor = userMailRegistrationProcessor;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public MMTResponseCreator createUserAccount(ValidUserRegistrationRequest validUserRegistrationRequest) {
        log.info("Starting processing registration new user account with data: {}", validUserRegistrationRequest);
        if (userRepository.existsByNicknameOrMail(validUserRegistrationRequest.getNickname(), validUserRegistrationRequest.getMail())) {
            log.error(
                    "End of processing registration new user account with data: {}, with error: {}",
                    validUserRegistrationRequest,
                    GIVEN_USER_ALREADY_EXISTS);
            return new Conflict(GIVEN_USER_ALREADY_EXISTS);
        }
        UserRegistrationResponseDto responseDto = userAuthManagement.createUserAccount(validUserRegistrationRequest);
        userMailRegistrationProcessor.sendEmailRegistrationConfirmation(responseDto);
        log.info("End of processing registration new user account with response: {}", responseDto);
        return new Created(responseDto);
    }

    public MMTResponseCreator logInAccount(ValidUserLoginRequest validUserLoginRequest) {
        log.info("Starting processing logging in user account with data: {}", validUserLoginRequest);
        return userAuthManagement.authenticate(validUserLoginRequest.getNickname(), validUserLoginRequest.getPassword())
                .map(it -> userRepository.findByNickname(validUserLoginRequest.getNickname())
                        .map(userAuthManagement::logInAccount)
                        .map(userLoginResponseDto -> {
                            log.info("end of processing logging in user account with response: {}", userLoginResponseDto);
                            return (MMTResponseCreator) new Ok(userLoginResponseDto);
                        })
                        .orElseGet(() -> {
                            log.warn("end of processing logging in user account with result: {}", GIVEN_USER_WAS_NOT_FOUND);
                            return new NotFound(GIVEN_USER_WAS_NOT_FOUND);
                        }))
                .fold(this::switchResponseInCaseOfError, response -> response);
    }

    public MMTResponseCreator verifyUser(ValidUserRegisterConfirmation validUserRegisterConfirmation) {
        log.info("Start processing verifying user account with data: {}", validUserRegisterConfirmation);
        return userRepository.findByNickname(validUserRegisterConfirmation.getNickname())
                .<Either<MMTResponseCreator, User>>map(Either::right)
                .orElseGet(() -> {
                    log.error(
                            "End of processing verification user account with data: {}, with error: {}",
                            validUserRegisterConfirmation,
                            GIVEN_USER_WAS_NOT_FOUND);
                    return Either.left(new NotFound(GIVEN_USER_WAS_NOT_FOUND));
                })
                .flatMap(user -> this.checkIfGivenUserIAlreadyVerified(user, validUserRegisterConfirmation.getVerified()))
                .map(it -> userAuthManagement.verifyUserAccount(it, validUserRegisterConfirmation.getVerified()))
                .fold(error -> error, userVerificationResponseDto -> new Ok(userVerificationResponseDto));
    }

    private MMTResponseCreator switchResponseInCaseOfError(Integer error) {
        return switch (error) {
            case INVALID_LOGIN_CREDENTIALS -> new Forbidden();
            case INVALID_VERIFICATION_STATE -> new Unauthorised(INVALID_VERIFICATION_STATE);
            case INVALID_LOCK_STATE -> new Unauthorised(INVALID_LOCK_STATE);
            default -> new InternalServerError();
        };
    }

    private Either<MMTResponseCreator, User> checkIfGivenUserIAlreadyVerified(User user, boolean verificationState) {
        if(user.getIsVerified() == verificationState) {
            return Either.left(new Accepted(GIVEN_USER_VERIFICATION_STATE_IS_ALREADY_SET));
        }
        return Either.right(user);
    }
}

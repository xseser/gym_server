package com.gym.user.registration.service.processor;

import com.gym.user.registration.controller.request.valid.ValidUserLoginDto;
import com.gym.user.registration.controller.request.valid.ValidUserRegistrationRequest;
import com.gym.user.registration.repository.UserRepository;
import com.gym.user.registration.service.UserAuthManagement;
import com.gym.user.registration.service.validator.UserValidator;
import com.response.gym.response.Conflict;
import com.response.gym.response.Created;
import com.response.gym.response.MMTResponseCreator;
import com.response.gym.response.NotFound;
import com.response.gym.response.Ok;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.response.gym.controller.answer.UserAnswers.GIVEN_USER_ALREADY_EXISTS;
import static com.response.gym.controller.answer.UserAnswers.GIVEN_USER_WAS_NOT_FOUND;

@Component
@Slf4j
public class UserAuthProcessor {

    private final UserValidator registrationValidator;
    private final UserRepository userRepository;
    private final UserAuthManagement userAuthManagement;

    public UserAuthProcessor(
            UserValidator registrationValidator,
            UserRepository userRepository,
            UserAuthManagement userAuthManagement) {
        this.registrationValidator = registrationValidator;
        this.userRepository = userRepository;
        this.userAuthManagement = userAuthManagement;
    }

    public MMTResponseCreator createUserAccount(ValidUserRegistrationRequest validUserRegistrationRequest) {
        log.info("Starting processing registration new user account with data: {}", validUserRegistrationRequest);
        if (registrationValidator.isUserAccountUnique(validUserRegistrationRequest.getMail(), validUserRegistrationRequest.getNickname())) {
            return new Created(userAuthManagement.createUserAccount(validUserRegistrationRequest));
        }
        log.error(
                "End of processing registration new user account with data: {}, with error: {}",
                validUserRegistrationRequest,
                GIVEN_USER_ALREADY_EXISTS);
        return new Conflict(GIVEN_USER_ALREADY_EXISTS);
    }

    public MMTResponseCreator logInAccount(ValidUserLoginDto validUserLoginDto) {
        log.info("Starting processing logging in user account with data: {}", validUserLoginDto);
        return userRepository.findByMail(validUserLoginDto.getMail())
                .map(userAuthManagement::logInAccount)
                .map(it -> (MMTResponseCreator) new Ok(it))
                .orElseGet(() -> {
                    log.error(
                            "End of processing logging in user account with data: {}, with error: {}",
                            validUserLoginDto,
                            GIVEN_USER_WAS_NOT_FOUND);
                    return new NotFound(GIVEN_USER_WAS_NOT_FOUND);
                });
    }
}

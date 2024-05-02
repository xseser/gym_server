package com.gym.service.processor;

import com.gym.common.response.Conflict;
import com.gym.common.response.MMTResponseCreator;
import com.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.gym.service.UserAuthManagement;
import com.gym.service.validator.RegistrationValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.gym.common.controller.answer.UserAnswers.GIVEN_USER_ALREADY_EXISTS;

@Component
@Slf4j
public class UserRegistrationProcessor {

    private final RegistrationValidator registrationValidator;
    private final UserAuthManagement userAuthManagement;

    public UserRegistrationProcessor(
            RegistrationValidator registrationValidator,
            UserAuthManagement userAuthManagement) {
        this.registrationValidator = registrationValidator;
        this.userAuthManagement = userAuthManagement;
    }

    public MMTResponseCreator createUserAccount(ValidUserRegistrationRequest validUserRegistrationRequest) {
        log.info("Starting processing new user account creation with data: {}", validUserRegistrationRequest);
        if(registrationValidator.isUserAccountUnique(validUserRegistrationRequest.getMail(), validUserRegistrationRequest.getNickname())) {
            return userAuthManagement.createUserAccount(validUserRegistrationRequest);
        }
        log.error("Ending processing new user account creation for data: {}, with error: {}",
                validUserRegistrationRequest,
                GIVEN_USER_ALREADY_EXISTS);
        return new Conflict(GIVEN_USER_ALREADY_EXISTS);
    }
}

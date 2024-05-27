package com.backend.gym.controller.validator.impl.base;

import com.backend.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import lombok.extern.slf4j.Slf4j;

import static com.response.gym.controller.answer.UserAnswers.PASSWORDS_DOES_NOT_MATCH;

@Slf4j
public class PasswordMatcherComparison {

    public static ValidUserRegistrationRequest checkIfGivenPasswordsMatches(ValidUserRegistrationRequest validUserRegistrationRequest) {
        if (validUserRegistrationRequest.getPassword().equals(validUserRegistrationRequest.getPasswordMatcher())) {
            return validUserRegistrationRequest;
        }
        throw new IllegalStateException(String.valueOf(PASSWORDS_DOES_NOT_MATCH));
    }
}

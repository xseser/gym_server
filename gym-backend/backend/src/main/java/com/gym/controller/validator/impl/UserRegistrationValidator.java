package com.gym.controller.validator.impl;


import com.gym.controller.request.dto.base.UserRegistrationDto;
import com.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.gym.controller.validator.Validator;
import com.gym.controller.validator.ValidatorHelper;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.stereotype.Component;

import static com.gym.common.controller.answer.UserAnswers.INVALID_MAIL_CREDENTIALS;
import static com.gym.common.controller.answer.UserAnswers.INVALID_NICKNAME_CREDENTIALS;
import static com.gym.common.controller.answer.UserAnswers.INVALID_PASSWORD_CREDENTIALS;
import static com.gym.common.controller.answer.UserAnswers.PASSWORDS_DOES_NOT_MATCH;

@Component
public class UserRegistrationValidator extends Validator<UserRegistrationDto, ValidUserRegistrationRequest> {

    @Override
    public ValidUserRegistrationRequest validate(UserRegistrationDto userRegistrationDto) {
        ValidUserRegistrationRequest validUserRegistrationRequest = new ValidUserRegistrationRequest();
        ValidatorHelper<String> validatorHelper = new ValidatorHelper<>();

        validatorHelper.assignDataToValidRequest(
                string -> simpleStringValidator(string),
                Triple.of(validUserRegistrationRequest::setMail, userRegistrationDto.getMail(), INVALID_MAIL_CREDENTIALS),
                Triple.of(validUserRegistrationRequest::setPassword, userRegistrationDto.getPassword(), INVALID_PASSWORD_CREDENTIALS),
                Triple.of(validUserRegistrationRequest::setPasswordMatcher, userRegistrationDto.getPasswordMatcher(), INVALID_PASSWORD_CREDENTIALS),
                Triple.of(validUserRegistrationRequest::setNickname, userRegistrationDto.getNickName(), INVALID_NICKNAME_CREDENTIALS));

        return checkIfGivenPasswordsMatches(validUserRegistrationRequest);
    }

    private ValidUserRegistrationRequest checkIfGivenPasswordsMatches(ValidUserRegistrationRequest validUserRegistrationRequest) {
        if (validUserRegistrationRequest.getPassword().equals(validUserRegistrationRequest.getPasswordMatcher())) {
            return validUserRegistrationRequest;
        }
        throw new IllegalArgumentException(String.valueOf(PASSWORDS_DOES_NOT_MATCH));
    }
}

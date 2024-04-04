package com.gym.controller.validator.impl;

import com.gym.common.controller.dto.UserRegistrationDto;
import com.gym.common.response.Conflict;
import com.gym.common.response.MMTResponseCreator;
import com.gym.controller.processor.dto.ValidUserRegistrationRequest;
import com.gym.controller.validator.Validator;
import com.hubspot.algebra.Result;
import cyclops.control.Either;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.stereotype.Component;

import static com.gym.common.controller.answer.UserAnswers.PASSWORD_DOES_NOT_MATCH;

@Component
public class UserRegistrationValidator extends Validator<UserRegistrationDto, ValidUserRegistrationRequest> {

    @Override
    public Either<MMTResponseCreator, ValidUserRegistrationRequest> validate(UserRegistrationDto userRegistrationDto) {
        ValidUserRegistrationRequest validUserRegistrationRequest = new ValidUserRegistrationRequest();

        assignDataToValidRequest(
                Triple.of(validUserRegistrationRequest::setMail, userRegistrationDto.getMail(), 1),
                Triple.of(validUserRegistrationRequest::setPassword, userRegistrationDto.getPassword(), 2),
                Triple.of(validUserRegistrationRequest::setPasswordMatcher, userRegistrationDto.getPasswordConfirm(), 3),
                Triple.of(validUserRegistrationRequest::setNickname, userRegistrationDto.getNickName(), 4));

        Result<ValidUserRegistrationRequest, MMTResponseCreator> result = checkIfGivenPasswordsMatches(validUserRegistrationRequest);
        if(result.isOk()) {
            return Either.right(result.expect("3")); //TODO
        }
        return Either.left(result.unwrapErrOrElseThrow()); //TODO
    }

    private Result<ValidUserRegistrationRequest, MMTResponseCreator> checkIfGivenPasswordsMatches(ValidUserRegistrationRequest validUserRegistrationRequest) {
        if (validUserRegistrationRequest.getPassword().equals(validUserRegistrationRequest.getPasswordMatcher())) {
            return Result.ok(new ValidUserRegistrationRequest());
        }
        return Result.err(new Conflict(PASSWORD_DOES_NOT_MATCH));
    }
}

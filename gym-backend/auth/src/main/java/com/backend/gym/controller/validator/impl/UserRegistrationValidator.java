package com.backend.gym.controller.validator.impl;

import com.backend.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.backend.gym.controller.request.dto.base.UserRegistrationDto;
import com.core.gym.validator.Validator;
import com.core.gym.validator.ValidatorHelper;
import com.response.gym.response.MMTResponseCreator;
import cyclops.control.Either;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import static com.backend.gym.controller.validator.impl.base.GenderValidator.genderValidator;
import static com.backend.gym.controller.validator.impl.base.MailValidator.mailValidator;
import static com.backend.gym.controller.validator.impl.base.NameValidator.nameValidator;
import static com.backend.gym.controller.validator.impl.base.PasswordMatcherValidator.passwordMatcherValidator;
import static com.backend.gym.controller.validator.impl.base.PasswordValidator.passwordValidator;

@Primary
@Component
public class UserRegistrationValidator extends Validator<UserRegistrationDto, ValidUserRegistrationRequest> {

    private final ValidatorHelper validatorHelper;

    public UserRegistrationValidator(ValidatorHelper validatorHelper) {
        this.validatorHelper = validatorHelper;
    }

    @Override
    public Either<Integer, ValidUserRegistrationRequest> validate(UserRegistrationDto userRegistrationDto) {
        ValidUserRegistrationRequest validUserRegistrationRequest = new ValidUserRegistrationRequest();
        return validatorHelper.validate(
                        nameValidator(userRegistrationDto.getNickName(), validUserRegistrationRequest::setNickname),
                        passwordValidator(userRegistrationDto.getPassword(), validUserRegistrationRequest::setPassword),
                        passwordValidator(userRegistrationDto.getPasswordMatcher(), validUserRegistrationRequest::setPasswordMatcher),
                        mailValidator(userRegistrationDto.getMail(), validUserRegistrationRequest::setMail),
                        genderValidator(userRegistrationDto.getGender(), validUserRegistrationRequest::setGender),
                        passwordMatcherValidator(validUserRegistrationRequest.getPassword(), validUserRegistrationRequest.getPasswordMatcher()))
                .<Either<Integer, ValidUserRegistrationRequest>>
                        map(Either::left)
                .orElseGet(()-> Either.right(validUserRegistrationRequest));
    }

}

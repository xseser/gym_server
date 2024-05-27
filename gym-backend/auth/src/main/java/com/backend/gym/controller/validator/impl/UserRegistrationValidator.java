package com.backend.gym.controller.validator.impl;

import com.backend.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.backend.gym.controller.request.dto.base.UserRegistrationDto;
import com.core.gym.validator.Validator;
import com.core.gym.validator.ValidatorHelper;
import com.backend.gym.model.user.Gender;
import org.springframework.stereotype.Component;

import static com.backend.gym.controller.validator.impl.base.GenderValidator.genderValidator;
import static com.backend.gym.controller.validator.impl.base.MailValidator.mailValidator;
import static com.backend.gym.controller.validator.impl.base.NameValidator.nameValidator;
import static com.backend.gym.controller.validator.impl.base.PasswordMatcherComparison.checkIfGivenPasswordsMatches;
import static com.backend.gym.controller.validator.impl.base.PasswordValidator.passwordValidator;

@Component
public class UserRegistrationValidator extends Validator<UserRegistrationDto, ValidUserRegistrationRequest> {

    private final ValidatorHelper<String> stringValidatorHelper;
    private final ValidatorHelper<Gender> genderValidatorHelper;

    public UserRegistrationValidator(ValidatorHelper<String> stringValidatorHelper, ValidatorHelper<Gender> genderValidatorHelper) {
        this.stringValidatorHelper = stringValidatorHelper;
        this.genderValidatorHelper = genderValidatorHelper;
    }

    @Override
    public ValidUserRegistrationRequest validate(UserRegistrationDto userRegistrationDto) {
        ValidUserRegistrationRequest validUserRegistrationRequest = new ValidUserRegistrationRequest();

        stringValidatorHelper.validateAndApplyFunction(
                () -> nameValidator(userRegistrationDto.getNickName()),
                validUserRegistrationRequest::setNickname);

        stringValidatorHelper.validateAndApplyFunction(
                () -> passwordValidator(userRegistrationDto.getPassword()),
                validUserRegistrationRequest::setPassword);

        stringValidatorHelper.validateAndApplyFunction(
                () -> passwordValidator(userRegistrationDto.getPasswordMatcher()),
                validUserRegistrationRequest::setPasswordMatcher);

        stringValidatorHelper.validateAndApplyFunction(
                () -> mailValidator(userRegistrationDto.getMail()),
                validUserRegistrationRequest::setMail);

        genderValidatorHelper.validateAndApplyFunction(
                () -> genderValidator(userRegistrationDto.getGender()),
                validUserRegistrationRequest::setGender);

        return checkIfGivenPasswordsMatches(validUserRegistrationRequest);
    }
}

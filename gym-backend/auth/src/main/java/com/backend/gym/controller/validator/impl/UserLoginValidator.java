package com.backend.gym.controller.validator.impl;

import com.backend.gym.controller.request.dto.base.UserLoginDto;
import com.backend.gym.controller.request.dto.valid.ValidUserLoginDto;
import com.core.gym.validator.Validator;
import com.core.gym.validator.ValidatorHelper;

import static com.backend.gym.controller.validator.impl.base.MailValidator.mailValidator;
import static com.backend.gym.controller.validator.impl.base.PasswordValidator.passwordValidator;

public class UserLoginValidator extends Validator<UserLoginDto, ValidUserLoginDto> {

    private final ValidatorHelper<String> stringValidatorHelper;

    public UserLoginValidator(ValidatorHelper<String> stringValidatorHelper) {
        this.stringValidatorHelper = stringValidatorHelper;
    }

    @Override
    public ValidUserLoginDto validate(UserLoginDto userLoginDto) {
        ValidUserLoginDto validUserLoginDto = new ValidUserLoginDto();

        stringValidatorHelper.validateAndApplyFunction(
                () -> mailValidator(userLoginDto.getMail()),
                validUserLoginDto::setMail);
        stringValidatorHelper.validateAndApplyFunction(
                () -> passwordValidator(userLoginDto.getPassword()),
                validUserLoginDto::setPassword);
        return validUserLoginDto;
    }
}

package com.backend.gym.controller.validator.impl;

import com.backend.gym.controller.request.dto.base.UserLoginDto;
import com.backend.gym.controller.request.dto.valid.ValidUserLoginDto;
import com.core.gym.validator.Validator;
import com.core.gym.validator.ValidatorHelper;
import cyclops.control.Either;
import org.springframework.stereotype.Component;

import static com.backend.gym.controller.validator.impl.base.MailValidator.mailValidator;
import static com.backend.gym.controller.validator.impl.base.PasswordValidator.passwordValidator;

@Component
public class UserLoginValidator extends Validator<UserLoginDto, ValidUserLoginDto> {

    private final ValidatorHelper validatorHelper;

    public UserLoginValidator(ValidatorHelper validatorHelper) {
        this.validatorHelper = validatorHelper;
    }

    @Override
    public Either<Integer, ValidUserLoginDto> validate(UserLoginDto userLoginDto) {
        ValidUserLoginDto validUserLoginDto = new ValidUserLoginDto();
        return validatorHelper.validate(
                        mailValidator(userLoginDto.getMail(), validUserLoginDto::setMail),
                        passwordValidator(userLoginDto.getPassword(), validUserLoginDto::setPassword))
                .<Either<Integer, ValidUserLoginDto>>
                        map(Either::left)
                .orElseGet(()-> Either.right(validUserLoginDto));
    }
}

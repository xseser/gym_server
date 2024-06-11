package com.gym.user.registration.controller.validator;

import com.core.gym.validator.Validator;
import com.core.gym.validator.ValidatorHelper;
import com.gym.user.registration.controller.request.base.UserLoginDto;
import com.gym.user.registration.controller.request.valid.ValidUserLoginDto;
import cyclops.control.Either;
import org.springframework.stereotype.Component;

import static com.gym.user.registration.controller.validator.base.MailValidator.mailValidator;
import static com.gym.user.registration.controller.validator.base.PasswordValidator.passwordValidator;

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
                .orElseGet(() -> Either.right(validUserLoginDto));
    }
}

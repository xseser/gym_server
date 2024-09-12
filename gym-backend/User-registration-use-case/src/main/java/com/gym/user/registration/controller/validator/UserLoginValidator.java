package com.gym.user.registration.controller.validator;

import com.core.gym.validator.Validator;
import com.core.gym.validator.ValidatorHelper;
import com.gym.user.registration.controller.request.base.UserLoginDto;
import com.gym.user.registration.controller.request.valid.ValidUserLoginRequest;
import cyclops.control.Either;
import org.springframework.stereotype.Component;

import static com.gym.user.registration.controller.validator.base.NameValidator.nameValidator;
import static com.gym.user.registration.controller.validator.base.PasswordValidator.passwordValidator;

@Component
public class UserLoginValidator extends Validator<UserLoginDto, ValidUserLoginRequest> {

    private final ValidatorHelper validatorHelper;

    public UserLoginValidator(ValidatorHelper validatorHelper) {
        this.validatorHelper = validatorHelper;
    }

    @Override
    public Either<Integer, ValidUserLoginRequest> validate(UserLoginDto userLoginDto) {
        ValidUserLoginRequest validUserLoginRequest = new ValidUserLoginRequest();
        return validatorHelper.validate(
                        nameValidator(userLoginDto.getNickname(), validUserLoginRequest::setNickname),
                        passwordValidator(userLoginDto.getPassword(), validUserLoginRequest::setPassword))
                .<Either<Integer, ValidUserLoginRequest>>
                        map(Either::left)
                .orElseGet(() -> Either.right(validUserLoginRequest));
    }
}

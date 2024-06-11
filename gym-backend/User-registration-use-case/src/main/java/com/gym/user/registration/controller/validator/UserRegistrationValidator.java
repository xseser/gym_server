package com.gym.user.registration.controller.validator;

import com.core.gym.validator.Validator;
import com.core.gym.validator.ValidatorHelper;
import com.gym.user.registration.controller.request.base.UserRegistrationDto;
import com.gym.user.registration.controller.request.valid.ValidUserRegistrationRequest;
import cyclops.control.Either;
import org.springframework.stereotype.Component;

import static com.gym.user.registration.controller.validator.base.GenderValidator.genderValidator;
import static com.gym.user.registration.controller.validator.base.MailValidator.mailValidator;
import static com.gym.user.registration.controller.validator.base.NameValidator.nameValidator;
import static com.gym.user.registration.controller.validator.base.PasswordMatcherValidator.passwordMatcherValidator;
import static com.gym.user.registration.controller.validator.base.PasswordValidator.passwordValidator;

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
                .orElseGet(() -> Either.right(validUserRegistrationRequest));
    }
}

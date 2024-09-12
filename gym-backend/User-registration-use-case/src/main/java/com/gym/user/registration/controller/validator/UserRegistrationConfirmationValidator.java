package com.gym.user.registration.controller.validator;

import com.core.gym.validator.Validator;
import com.core.gym.validator.ValidatorHelper;
import com.gym.user.registration.controller.request.base.UserRegisterConfirmation;
import com.gym.user.registration.controller.request.valid.ValidUserRegisterConfirmation;
import cyclops.control.Either;
import org.springframework.stereotype.Component;

import static com.gym.user.registration.controller.validator.base.NameValidator.nameValidator;
import static com.gym.user.registration.controller.validator.base.VerificationValidator.verificationFlagValidator;

@Component
public class UserRegistrationConfirmationValidator extends Validator<UserRegisterConfirmation, ValidUserRegisterConfirmation> {

    private final ValidatorHelper validatorHelper;

    public UserRegistrationConfirmationValidator(ValidatorHelper validatorHelper) {
        this.validatorHelper = validatorHelper;
    }

    @Override
    public Either<Integer, ValidUserRegisterConfirmation> validate(UserRegisterConfirmation userRegisterConfirmation) {
        ValidUserRegisterConfirmation validUserRegisterConfirmation = new ValidUserRegisterConfirmation();
        return validatorHelper.validate(
                nameValidator(userRegisterConfirmation.getNickname(), validUserRegisterConfirmation::setNickname),
                verificationFlagValidator(userRegisterConfirmation.getIsVerified(), validUserRegisterConfirmation::setVerified))
                .<Either<Integer, ValidUserRegisterConfirmation>>
                        map(Either::left)
                .orElseGet(() -> Either.right(validUserRegisterConfirmation));
    }
}

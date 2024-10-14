package com.gym.user.registration.controller.validator;

import com.core.gym.validator.Validator;
import com.core.gym.validator.ValidatorHelper;
import com.gym.user.registration.controller.request.base.RefreshTokenDto;
import com.gym.user.registration.controller.request.valid.ValidRefreshTokenRequest;
import cyclops.control.Either;
import org.springframework.stereotype.Component;

import static com.gym.user.registration.controller.validator.base.NullableObjectValidator.nullableValidator;

@Component
public class RefreshTokenValidator extends Validator<RefreshTokenDto, ValidRefreshTokenRequest> {

    private final ValidatorHelper validatorHelper;

    public RefreshTokenValidator(ValidatorHelper validatorHelper) {
        this.validatorHelper = validatorHelper;
    }

    @Override
    public Either<Integer, ValidRefreshTokenRequest> validate(RefreshTokenDto refreshTokenDto) {
        ValidRefreshTokenRequest validRefreshTokenRequest = new ValidRefreshTokenRequest();
        return validatorHelper.validate(
                nullableValidator(refreshTokenDto.getRefreshToken(), validRefreshTokenRequest::setRefreshToken))
                .<Either<Integer, ValidRefreshTokenRequest>>
                        map(Either::left)
                .orElseGet(() -> Either.right(validRefreshTokenRequest));
    }
}

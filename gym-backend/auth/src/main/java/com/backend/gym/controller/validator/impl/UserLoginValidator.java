package com.backend.gym.controller.validator.impl;

import com.backend.gym.controller.request.dto.base.UserLoginDto;
import com.backend.gym.controller.request.dto.valid.ValidUserLoginDto;
import com.core.gym.validator.Validator;
import com.core.gym.validator.ValidatorHelper;
import com.core.gym.validator.ValidatorHelperV2;
import com.response.gym.response.BadRequest;
import com.response.gym.response.MMTResponseCreator;
import cyclops.control.Either;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import static com.backend.gym.controller.validator.impl.base.MailValidator.mailValidator;
import static com.backend.gym.controller.validator.impl.base.PasswordValidator.passwordValidator;

@Component
public class UserLoginValidator extends Validator<UserLoginDto, ValidUserLoginDto> {

    private final ValidatorHelperV2 validatorHelperV2;

    public UserLoginValidator(ValidatorHelperV2 validatorHelperV2) {
        this.validatorHelperV2 = validatorHelperV2;
    }

    @Override
    public Either<MMTResponseCreator, ValidUserLoginDto> validate(UserLoginDto userLoginDto) {
        ValidUserLoginDto validUserLoginDto = new ValidUserLoginDto();
        return validatorHelperV2.validate(
                        mailValidator(userLoginDto.getMail(), validUserLoginDto::setMail),
                        passwordValidator(userLoginDto.getPassword(), validUserLoginDto::setPassword))
                .<Either<MMTResponseCreator, ValidUserLoginDto>>
                        map(Either::left)
                .orElseGet(()-> Either.right(validUserLoginDto));
    }
}

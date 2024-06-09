package com.backend.gym.controller.validator.impl;

import com.backend.gym.controller.request.dto.base.UserRegistrationDto;
import com.backend.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.backend.gym.model.user.User;
import com.backend.gym.repository.UserRepository;
import com.response.gym.response.BadRequest;
import com.response.gym.response.MMTResponseCreator;
import cyclops.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseIntegrationTest {

    @Autowired
    protected UserRegistrationValidator userRegistrationValidator;

    protected void validateValidUserRegistrationRequest(UserRegistrationDto userRegistrationDto) {
        //when
        Either<Integer, ValidUserRegistrationRequest> afterValidation = userRegistrationValidator.validate(userRegistrationDto);
        log.info("Object after validation: {}", afterValidation);

        //then
        assertUserRegistrationToValidUserRegistration(userRegistrationDto, afterValidation);
    }

    protected void validateInvalidUserRegistrationRequest(UserRegistrationDto userRegistrationDto, int errorCode) {
        //when
        Either<Integer, ValidUserRegistrationRequest> afterValidation = userRegistrationValidator.validate(userRegistrationDto);
        log.info("Object after validation: {}", afterValidation);

        //then
        Assertions
                .assertThat(afterValidation
                        .getLeft()
                        .toOptional()
                        .orElseThrow())
                .isEqualTo(errorCode);
    }

    protected void assertUserRegistrationToValidUserRegistration(
            UserRegistrationDto userRegistrationDto,
            Either<Integer, ValidUserRegistrationRequest> afterValidation) {

        ValidUserRegistrationRequest validUserRegistrationRequest = afterValidation.get()
                .toOptional()
                .orElseThrow(IllegalArgumentException::new);

        Assertions.assertThat(userRegistrationDto.getMail()).isEqualTo(validUserRegistrationRequest.getMail());
        Assertions.assertThat(userRegistrationDto.getPassword()).isEqualTo(validUserRegistrationRequest.getPassword());
        Assertions.assertThat(userRegistrationDto.getPasswordMatcher()).isEqualTo(validUserRegistrationRequest.getPasswordMatcher());
        Assertions.assertThat(userRegistrationDto.getNickName()).isEqualTo(validUserRegistrationRequest.getNickname());

        if (userRegistrationDto.getGender() != null) {
            Assertions.assertThat(userRegistrationDto.getGender()).isEqualTo(validUserRegistrationRequest.getGender().toString());
        }
    }
}

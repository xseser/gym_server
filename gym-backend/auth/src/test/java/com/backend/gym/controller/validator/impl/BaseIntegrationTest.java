package com.backend.gym.controller.validator.impl;

import com.backend.gym.controller.request.dto.base.UserRegistrationDto;
import com.backend.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseIntegrationTest {

    @Autowired
    protected UserRegistrationValidator userRegistrationValidator;

    protected void validateInvalidUserRegistrationRequest(UserRegistrationDto userRegistrationDto, int code) {
        try {
            //when
            userRegistrationValidator.validate(userRegistrationDto);
        } catch (IllegalStateException errorCode) {

            //then
            Assertions
                    .assertThat(errorCode.getMessage())
                    .isEqualTo(String.valueOf(code));
        }
    }

    protected void validateValidUserRegistrationRequest(UserRegistrationDto userRegistrationDto) {
        //when
        ValidUserRegistrationRequest validUserRegistrationRequest = userRegistrationValidator.validate(userRegistrationDto);

        //then
        assertUserRegistrationToValidUserRegistration(userRegistrationDto, validUserRegistrationRequest);
    }

    protected void assertUserRegistrationToValidUserRegistration(UserRegistrationDto userRegistrationDto,
            ValidUserRegistrationRequest validUserRegistrationRequest) {
        Assertions.assertThat(userRegistrationDto.getMail()).isEqualTo(validUserRegistrationRequest.getMail());
        Assertions.assertThat(userRegistrationDto.getPassword()).isEqualTo(validUserRegistrationRequest.getPassword());
        Assertions.assertThat(userRegistrationDto.getPasswordMatcher()).isEqualTo(validUserRegistrationRequest.getPasswordMatcher());
        Assertions.assertThat(userRegistrationDto.getNickName()).isEqualTo(validUserRegistrationRequest.getNickname());

        if(userRegistrationDto.getGender() != null) {
            Assertions.assertThat(userRegistrationDto.getGender()).isEqualTo(validUserRegistrationRequest.getGender().toString());
        }
    }
}

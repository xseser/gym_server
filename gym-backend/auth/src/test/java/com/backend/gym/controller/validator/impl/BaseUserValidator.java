package com.backend.gym.controller.validator.impl;

import com.backend.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.backend.gym.model.user.Gender;
import com.backend.gym.controller.request.dto.base.UserRegistrationDto;

public interface BaseUserValidator {

    String mail = "random.mail@example.com";
    String nickname = "randomNickName";
    String password = "Password123!";
    Gender gender = Gender.MAN;

    default UserRegistrationDto provideUserRegistrationData() {
        return UserRegistrationDto.builder()
                .mail(this.mail)
                .nickName(this.nickname)
                .password(this.password)
                .passwordMatcher(this.password)
                .gender(String.valueOf(this.gender))
                .build();
    }

    default ValidUserRegistrationRequest provideValidUserRegistrationData() {
        return new ValidUserRegistrationRequest(this.password, this.password, this.mail, this.nickname, this.gender);
    }
}
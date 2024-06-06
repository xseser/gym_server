package com.gym.user.registration.service;

import com.gym.user.registration.controller.request.base.UserRegistrationDto;
import com.gym.user.registration.controller.request.valid.ValidUserRegistrationRequest;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import com.gym.user.registration.model.Gender;
import com.gym.user.registration.model.Role;

import java.util.UUID;

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

    default UserRegistrationResponseDto mapValidUserRegistrationDataToResponse() {
        return new UserRegistrationResponseDto(
                UUID.randomUUID(),
                this.mail,
                this.nickname,
                this.gender,
                Role.MEMBER);
    }
}

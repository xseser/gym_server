package com.gym.user.registration.service;

import com.gym.user.registration.controller.request.valid.ValidUserLoginRequest;
import com.gym.user.registration.controller.request.valid.ValidUserRegisterConfirmation;
import com.gym.user.registration.controller.request.valid.ValidUserRegistrationRequest;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import com.gym.user.registration.controller.response.UserVerificationResponseDto;
import com.gym.user.registration.model.Gender;
import com.gym.user.registration.model.Role;
import com.gym.user.registration.model.User;

import java.util.UUID;

public interface BaseUserValidator {

    String mail = "random.mail@example.com";
    String nickname = "randomNickName";
    String password = "Password123!";
    Gender gender = Gender.MAN;
    UUID id = UUID.randomUUID();
    Role role = Role.MEMBER;
    boolean isLocked = false;
    boolean isVerified = false;
    String token = "IamGeneratedTokenForTests";

    default ValidUserRegistrationRequest provideValidUserRegistrationData() {
        return new ValidUserRegistrationRequest(this.password, this.password, this.mail, this.nickname, this.gender);
    }

    default ValidUserLoginRequest provideValidUserLoginData() {
        return new ValidUserLoginRequest(this.nickname, this.password);
    }

    default ValidUserRegisterConfirmation provideValidUserRegisterConfirmation(boolean shouldBeVerified) {
        return new ValidUserRegisterConfirmation(this.nickname, shouldBeVerified);
    }

    default UserRegistrationResponseDto mapValidUserRegistrationDataToResponse() {
        return new UserRegistrationResponseDto(
                this.id,
                this.mail,
                this.nickname,
                this.gender,
                this.role);
    }

    default UserVerificationResponseDto mapValidUserRegisterConfirmationToResponse(boolean shouldBeVerified) {
        return new UserVerificationResponseDto(this.id.toString(), this.nickname, this.role, shouldBeVerified);
    }

    default User provideUser() {
        return User.builder()
                .id(this.id)
                .mail(this.mail)
                .nickname(this.nickname)
                .password(this.password)
                .gender(this.gender)
                .role(this.role)
                .isLocked(this.isLocked)
                .isVerified(this.isVerified)
                .build();
    }
}

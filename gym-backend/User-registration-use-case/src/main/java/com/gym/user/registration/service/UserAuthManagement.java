package com.gym.user.registration.service;

import com.gym.user.registration.controller.request.valid.ValidUserRegistrationRequest;
import com.gym.user.registration.controller.response.UserLoginResponseDto;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import com.gym.user.registration.controller.response.UserVerificationResponseDto;
import com.gym.user.registration.model.User;
import com.response.gym.response.MMTResponseCreator;
import cyclops.control.Either;

public interface UserAuthManagement {

    UserRegistrationResponseDto createUserAccount(ValidUserRegistrationRequest validUserRegistrationRequest);

    UserLoginResponseDto logInAccount(User user);

    UserVerificationResponseDto verifyUserAccount(User user, Boolean isVerified);

    Either<Integer, Void> authenticate(String nickname, String password);
}

package com.gym.user.registration.service;

import com.gym.user.registration.controller.request.valid.ValidUserRegistrationRequest;
import com.gym.user.registration.controller.response.UserAuthenticationResponse;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import com.gym.user.registration.controller.response.UserVerificationResponseDto;
import com.gym.user.registration.model.User;
import cyclops.control.Either;

public interface UserAuthManagement {

    UserRegistrationResponseDto createUserAccount(ValidUserRegistrationRequest validUserRegistrationRequest);

    UserAuthenticationResponse generateTokens(User user);

    UserVerificationResponseDto verifyUserAccount(User user, Boolean isVerified);

    Either<Integer, Void> authenticate(String nickname, String password);
}

package com.gym.user.registration.service;

import com.gym.user.registration.controller.request.valid.ValidUserRegistrationRequest;
import com.gym.user.registration.controller.response.UserLoginResponseDto;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import com.gym.user.registration.model.User;

public interface UserAuthManagement {

    UserRegistrationResponseDto createUserAccount(ValidUserRegistrationRequest validUserRegistrationRequest);

    UserLoginResponseDto logInAccount(User user);
}

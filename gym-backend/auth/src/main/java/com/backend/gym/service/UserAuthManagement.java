package com.backend.gym.service;

import com.backend.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.backend.gym.model.user.User;
import com.backend.gym.service.response.UserLoginResponseDto;
import com.backend.gym.service.response.UserRegistrationResponseDto;

public interface UserAuthManagement {

    UserRegistrationResponseDto createUserAccount(ValidUserRegistrationRequest validUserRegistrationRequest);

    UserLoginResponseDto logInAccount(User user);
}

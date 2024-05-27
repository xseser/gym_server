package com.backend.gym.service;

import com.backend.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.backend.gym.model.user.User;
import com.response.gym.response.MMTResponseCreator;
import com.backend.gym.controller.request.dto.valid.ValidUserLoginDto;

public interface UserAuthManagement {

    MMTResponseCreator createUserAccount(ValidUserRegistrationRequest validUserRegistrationRequest);

    MMTResponseCreator logInAccount(User user);
}

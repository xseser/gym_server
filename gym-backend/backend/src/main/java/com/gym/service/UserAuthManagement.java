package com.gym.service;

import com.gym.common.response.MMTResponseCreator;
import com.gym.controller.request.dto.valid.ValidUserLoginDto;
import com.gym.controller.request.dto.valid.ValidUserRegistrationRequest;

public interface UserAuthManagement {

    MMTResponseCreator createUserAccount(ValidUserRegistrationRequest validUserRegistrationRequest);

    MMTResponseCreator logInAccount(ValidUserLoginDto validUserLoginDto);
}

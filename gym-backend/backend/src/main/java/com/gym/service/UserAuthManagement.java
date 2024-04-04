package com.gym.service;

import com.gym.common.response.MMTResponseCreator;
import com.gym.controller.processor.dto.ValidUserRegistrationRequest;

public interface UserAuthManagement {

    MMTResponseCreator createUserAccount(ValidUserRegistrationRequest validUserRegistrationRequest);
}

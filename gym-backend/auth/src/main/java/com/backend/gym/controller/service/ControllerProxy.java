package com.backend.gym.controller.service;

import com.backend.gym.controller.request.dto.base.UserLoginDto;
import com.backend.gym.controller.request.dto.base.UserRegistrationDto;
import com.response.gym.response.MMTResponseCreator;

public interface ControllerProxy {

    MMTResponseCreator createUserAccount(UserRegistrationDto userRegistrationDto);

    MMTResponseCreator loginAccount(UserLoginDto userLoginDto);
}

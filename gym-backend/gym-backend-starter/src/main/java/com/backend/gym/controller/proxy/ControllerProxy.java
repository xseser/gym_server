package com.backend.gym.controller.proxy;

import com.gym.user.registration.controller.request.base.UserLoginDto;
import com.gym.user.registration.controller.request.base.UserRegistrationDto;
import com.response.gym.response.MMTResponseCreator;

public interface ControllerProxy {

    MMTResponseCreator createUserAccount(UserRegistrationDto userRegistrationDto);

    MMTResponseCreator loginAccount(UserLoginDto userLoginDto);
}

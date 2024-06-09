package com.backend.gym.controller.service;

import com.backend.gym.controller.request.dto.base.UserLoginDto;
import com.backend.gym.controller.request.dto.base.UserRegistrationDto;
import com.backend.gym.controller.request.dto.valid.ValidUserLoginDto;
import com.backend.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.backend.gym.service.processor.UserAuthProcessor;
import com.core.gym.processor.ControllerProcessor;
import com.response.gym.response.MMTResponseCreator;
import org.springframework.stereotype.Service;

@Service
public class ControllerProxyService implements ControllerProxy {

    private final ControllerProcessor controllerProcessor;
    private final UserAuthProcessor userAuthProcessor;

    public ControllerProxyService(
            ControllerProcessor controllerProcessor,
            UserAuthProcessor userAuthProcessor) {
        this.controllerProcessor = controllerProcessor;
        this.userAuthProcessor = userAuthProcessor;
    }

    @Override
    public MMTResponseCreator createUserAccount(UserRegistrationDto userRegistrationDto) {
        return controllerProcessor.process(
                userRegistrationDto,
                it -> userAuthProcessor.createUserAccount((ValidUserRegistrationRequest) it));

    }

    @Override
    public MMTResponseCreator loginAccount(UserLoginDto userLoginDto) {
        return controllerProcessor.process(
                userLoginDto,
                it -> userAuthProcessor.logInAccount((ValidUserLoginDto) it));
    }
}

package com.backend.gym.controller.proxy;

import com.backend.gym.factory.ControllerProcessor;
import com.gym.user.registration.controller.request.base.UserLoginDto;
import com.gym.user.registration.controller.request.base.UserRegisterConfirmation;
import com.gym.user.registration.controller.request.base.UserRegistrationDto;
import com.gym.user.registration.controller.request.valid.ValidUserLoginRequest;
import com.gym.user.registration.controller.request.valid.ValidUserRegisterConfirmation;
import com.gym.user.registration.controller.request.valid.ValidUserRegistrationRequest;
import com.gym.user.registration.controller.validator.UserLoginValidator;
import com.gym.user.registration.controller.validator.UserRegistrationConfirmationValidator;
import com.gym.user.registration.controller.validator.UserRegistrationValidator;
import com.gym.user.registration.service.processor.UserAuthProcessor;
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
                UserRegistrationValidator.class,
                it -> userAuthProcessor.createUserAccount((ValidUserRegistrationRequest) it));
    }

    @Override
    public MMTResponseCreator loginAccount(UserLoginDto userLoginDto) {
        return controllerProcessor.process(
                userLoginDto,
                UserLoginValidator.class,
                it -> userAuthProcessor.logInAccount((ValidUserLoginRequest) it));
    }

    @Override
    public MMTResponseCreator verifyUser(UserRegisterConfirmation userRegisterConfirmation) {
        return controllerProcessor.process(
                userRegisterConfirmation,
                UserRegistrationConfirmationValidator.class,
                it -> userAuthProcessor.verifyUser((ValidUserRegisterConfirmation) it));
    }
}

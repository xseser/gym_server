package com.gym.controller;

import com.gym.controller.processor.ControllerProcessor;
import com.gym.controller.request.dto.base.UserLoginDto;
import com.gym.controller.request.dto.base.UserRegistrationDto;
import com.gym.controller.request.dto.valid.ValidUserLoginDto;
import com.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.gym.service.UserAuthManagement;
import com.gym.service.processor.UserRegistrationProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.gym.common.controller.url.UrlManagement.API_BASE;
import static com.gym.common.controller.url.UrlManagement.USER_LOGIN;
import static com.gym.common.controller.url.UrlManagement.USER_REGISTRATION;

@RestController
@Slf4j
public class UserManagementController extends ExceptionHandlerController {

    private final ControllerProcessor controllerProcessor;
    private final UserAuthManagement userAuthManagement;
    private final UserRegistrationProcessor userRegistrationProcessor;

    public UserManagementController(ControllerProcessor controllerProcessor, UserAuthManagement userAuthManagement,
            UserRegistrationProcessor userRegistrationProcessor) {
        this.controllerProcessor = controllerProcessor;
        this.userAuthManagement = userAuthManagement;
        this.userRegistrationProcessor = userRegistrationProcessor;
    }

    @RequestMapping(method = RequestMethod.POST, value = API_BASE + USER_REGISTRATION)
    public ResponseEntity registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        log.info("received request to register new user account with data: {}", userRegistrationDto);
        return controllerProcessor
                .process(userRegistrationDto, it -> userRegistrationProcessor.createUserAccount((ValidUserRegistrationRequest) it))
                .makeResponse();
    }

    @RequestMapping(method = RequestMethod.POST, value = API_BASE + USER_LOGIN)
    public ResponseEntity loginUser(@RequestBody UserLoginDto userLoginDto) {
        log.info("received request to log in user with data: {}", userLoginDto);
        return controllerProcessor
                .process(userLoginDto, it -> userAuthManagement.logInAccount((ValidUserLoginDto) it))
                .makeResponse();
    }
}

package com.backend.gym.controller;

import com.backend.gym.controller.request.dto.base.UserLoginDto;
import com.backend.gym.controller.request.dto.base.UserRegistrationDto;
import com.backend.gym.controller.service.ControllerProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.response.gym.controller.url.UrlManagement.API_BASE;
import static com.response.gym.controller.url.UrlManagement.USER_LOGIN;
import static com.response.gym.controller.url.UrlManagement.USER_REGISTRATION;

@RestController
@Slf4j
public class UserManagementController {

    private final ControllerProxy controllerProxy;

    public UserManagementController(ControllerProxy controllerProxy) {
        this.controllerProxy = controllerProxy;
    }

    @RequestMapping(method = RequestMethod.POST, value = API_BASE + USER_REGISTRATION)
    public ResponseEntity registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        log.info("received request to register new user account with data: {}", userRegistrationDto);
        return controllerProxy.createUserAccount(userRegistrationDto)
                .makeResponse();
    }

    @RequestMapping(method = RequestMethod.POST, value = API_BASE + USER_LOGIN)
    public ResponseEntity loginUser(@RequestBody UserLoginDto userLoginDto) {
        log.info("received request to log in user with data: {}", userLoginDto);
        return controllerProxy.loginAccount(userLoginDto)
                .makeResponse();
    }
}

package com.gym.controller;

import com.gym.common.controller.dto.UserRegistrationDto;
import com.gym.controller.processor.UserManagementControllerProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.gym.common.controller.url.UrlManagement.API_BASE;
import static com.gym.common.controller.url.UrlManagement.USER_REGISTRATION;

@RestController
@Slf4j
public class UserManagementController extends ExceptionHandlerController {

    private final UserManagementControllerProcessor userManagementControllerProcessor;

    public UserManagementController(UserManagementControllerProcessor userManagementControllerProcessor) {
        this.userManagementControllerProcessor = userManagementControllerProcessor;
    }

    @RequestMapping(method = RequestMethod.POST, value = API_BASE + USER_REGISTRATION)
    public ResponseEntity registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        log.info("received request to register new user account with data: {}", userRegistrationDto);
        return userManagementControllerProcessor
                .processUserRegistrationRequest(userRegistrationDto)
                .makeResponse();
    }
}

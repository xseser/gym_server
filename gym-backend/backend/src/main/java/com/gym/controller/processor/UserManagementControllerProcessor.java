package com.gym.controller.processor;

import com.gym.common.controller.dto.UserRegistrationDto;
import com.gym.common.response.MMTResponseCreator;
import com.gym.controller.processor.dto.ValidUserRegistrationRequest;
import com.gym.controller.validator.Validator;
import com.gym.service.UserAuthManagement;
import org.springframework.stereotype.Component;

@Component
public class UserManagementControllerProcessor {

    private final UserAuthManagement userAuthManagement;
    private final Validator validator;

    public UserManagementControllerProcessor(UserAuthManagement userAuthManagement, Validator validator) {
        this.userAuthManagement = userAuthManagement;
        this.validator = validator;
    }

    public MMTResponseCreator processUserRegistrationRequest(UserRegistrationDto userRegistrationDto) {
        return validator.validateAndInsertBehaviour(
                userRegistrationDto,
                it -> userAuthManagement.createUserAccount((ValidUserRegistrationRequest) it));
    }
}

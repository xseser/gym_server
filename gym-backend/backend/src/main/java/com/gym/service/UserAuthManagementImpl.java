package com.gym.service;

import com.gym.common.response.MMTResponseCreator;
import com.gym.controller.processor.dto.ValidUserRegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class UserAuthManagementImpl implements UserAuthManagement {


    @Override public MMTResponseCreator createUserAccount(ValidUserRegistrationRequest validUserRegistrationRequest) {
        return null;
    }
}

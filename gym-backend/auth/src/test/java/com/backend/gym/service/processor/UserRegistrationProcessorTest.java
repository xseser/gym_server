package com.backend.gym.service.processor;

import com.response.gym.response.MMTResponseCreator;
import com.backend.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.backend.gym.controller.validator.impl.BaseUserValidator;
import com.backend.gym.service.UserAuthManagement;
import com.backend.gym.service.validator.UserValidator;
import com.response.gym.response.Created;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRegistrationProcessorTest implements BaseUserValidator {


    @InjectMocks
    private UserAuthProcessor userRegistrationProcessor;

    @Mock
    private UserValidator registrationValidator;

    @Mock
    private UserAuthManagement userAuthManagement;

    @Test
    public void creatingNewAccountWithUniqueData_causesCreatedResponse() {
        //given
        ValidUserRegistrationRequest validUserRegistrationRequest = provideValidUserRegistrationData();

        when(registrationValidator.isUserAccountUnique(any(String.class), any(String.class)))
                .thenReturn(true);
        when(userAuthManagement.createUserAccount(any(ValidUserRegistrationRequest.class)))
                .thenReturn(new Created());
        //when
        MMTResponseCreator response = userRegistrationProcessor.createUserAccount(validUserRegistrationRequest);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(new Created().getStatusCode());
    }
}
package com.backend.gym.service.validator;

import com.backend.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.backend.gym.controller.validator.impl.BaseUserValidator;
import com.backend.gym.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserValidatorTest implements BaseUserValidator {

    @InjectMocks
    private UserValidator userValidator;

    @Mock
    private UserRepository userRepository;

    @Test
    public void uniqueMailAndUniqueNickNameWillReturnTrue() {
//        //given
//        ValidUserRegistrationRequest validUserRegistrationRequest = provideValidUserRegistrationData();
//
//        //when
//        Mockito
//                .when(userRepository.findByMail(validUserRegistrationRequest.getMail()))
//                .thenReturn()
//
//        userValidator.isUserAccountUnique()
        //TODO
    }
}
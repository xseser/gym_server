package com.gym.user.registration.service.validator;

import com.gym.user.registration.repository.UserRepository;
import com.gym.user.registration.service.BaseUserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
package com.gym.user.registration.service;

import com.gym.user.registration.controller.request.valid.ValidUserRegistrationRequest;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import com.gym.user.registration.model.User;
import com.gym.user.registration.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAuthManagementImplTest implements BaseUserValidator {

    @InjectMocks
    private UserAuthManagementImpl userAuthManagement;

    @Mock
    private CustomPasswordEncoder customPasswordEncoder;

    @Mock
    private UserRepository userRepository;

    @Test
    public void createUserAccountProvidesCorrectAssignment() {
        ValidUserRegistrationRequest validUserRegistrationRequest = provideValidUserRegistrationData();
        User user = provideUserResponse();

        when(customPasswordEncoder.encode(any()))
                .thenReturn(validUserRegistrationRequest.getPassword());

        when(userRepository.save(any()))
                .thenReturn(user);

        UserRegistrationResponseDto userRegistrationResponseDto = userAuthManagement.createUserAccount(validUserRegistrationRequest);

        Assertions.assertThat(userRegistrationResponseDto).isEqualTo(mapValidUserRegistrationDataToResponse());
    }
}
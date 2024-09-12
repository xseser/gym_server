package com.gym.user.registration.service;

import com.gym.user.registration.controller.request.valid.ValidUserRegistrationRequest;
import com.gym.user.registration.controller.response.UserLoginResponseDto;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import com.gym.user.registration.controller.response.UserVerificationResponseDto;
import com.gym.user.registration.model.User;
import com.gym.user.registration.repository.UserRepository;
import cyclops.control.Either;
import gym.mmt.auth.config.JwtService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.method.GlobalMethodSecurityBeanDefinitionParser;
import org.springframework.security.core.Authentication;

import static com.response.gym.controller.answer.UserAnswers.INVALID_VERIFICATION_STATE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAuthManagementImplTest implements BaseUserValidator {

    @InjectMocks
    private UserAuthManagementImpl userAuthManagement;

    @Mock
    private CustomPasswordEncoder customPasswordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    public void createUserAccountProvidesCorrectAssignment() {
        ValidUserRegistrationRequest validUserRegistrationRequest = provideValidUserRegistrationData();
        User user = provideUser();

        when(customPasswordEncoder.encode(any()))
                .thenReturn(validUserRegistrationRequest.getPassword());

        when(userRepository.save(any()))
                .thenReturn(user);

        UserRegistrationResponseDto userRegistrationResponseDto = userAuthManagement.createUserAccount(validUserRegistrationRequest);

        Assertions.assertThat(userRegistrationResponseDto).isEqualTo(mapValidUserRegistrationDataToResponse());
    }

    @Test
    public void loginUserAccountProvidesTokenInResponse() {
        //given
        User user = provideUser();
        when(jwtService.generateToken(eq(user))).thenReturn(token);

        //when
        UserLoginResponseDto userLoginResponseDto = userAuthManagement.logInAccount(user);

        //then
        Assertions.assertThat(userLoginResponseDto.getToken())
                .isEqualTo(token);
        Assertions.assertThat(userLoginResponseDto.getNickname())
                .isEqualTo(user.getUsername());
    }

    @Test
    public void verifyUserAccountChangesAccountVerificationState() {
        //given
        User user = provideUser();
        user.setIsVerified(true);
        when(userRepository.save(any(User.class))).thenReturn(user);

        //when
        UserVerificationResponseDto userVerificationResponseDto = userAuthManagement.verifyUserAccount(user, true);

        //then
        UserVerificationResponseDto expectedResponse = new UserVerificationResponseDto(id.toString(), nickname, role, true);

        Assertions
                .assertThat(userVerificationResponseDto)
                .isEqualTo(expectedResponse);
    }

    @Test
    public void authenticateUser_whenExceptionIsThrown_thenCorrectCodeIsReturned() {
        //given
        User user = provideUser();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getNickname(), user.getPassword());
        when(authenticationManager.authenticate(eq(auth)))
                .thenThrow(new DisabledException("User is disabled"));

        Either<Integer, Void> response = userAuthManagement.authenticate(user.getNickname(), user.getPassword());

        Assertions
                .assertThat(response.getLeft().orElse(null))
                .isEqualTo(INVALID_VERIFICATION_STATE);
    }

    @Test
    public void authenticateUser_whenExceptionIsNotThrown_thenRightSideValueIsReturned() {
        //given
        User user = provideUser();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getNickname(), user.getPassword());
        when(authenticationManager.authenticate(eq(auth))).thenReturn(null);

        Either<Integer, Void> response = userAuthManagement.authenticate(user.getNickname(), user.getPassword());

        Assertions
                .assertThat(response.getLeft().orElse(-20))
                .isEqualTo(-20);
    }
}
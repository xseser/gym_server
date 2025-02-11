package com.gym.user.registration.service.processor;

import com.gym.kafka.producer.model.VerificationStateToChange;
import com.gym.user.registration.controller.request.valid.ValidUserLoginRequest;
import com.gym.user.registration.controller.request.valid.ValidUserRegisterConfirmation;
import com.gym.user.registration.controller.request.valid.ValidUserRegistrationRequest;
import com.gym.user.registration.controller.response.UserAuthenticationResponse;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import com.gym.user.registration.controller.response.UserVerificationResponseDto;
import com.gym.user.registration.model.User;
import com.gym.user.registration.repository.UserRepository;
import com.gym.user.registration.service.BaseUserValidator;
import com.gym.user.registration.service.UserAuthManagement;
import com.response.gym.response.Accepted;
import com.response.gym.response.Conflict;
import com.response.gym.response.Created;
import com.response.gym.response.types.CommonResponse;
import com.response.gym.response.types.ErrorResponse;
import com.response.gym.response.Forbidden;
import com.response.gym.response.MMTResponseCreator;
import com.response.gym.response.NotFound;
import com.response.gym.response.Ok;
import cyclops.control.Either;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.response.gym.controller.answer.UserAnswers.GIVEN_USER_VERIFICATION_STATE_IS_ALREADY_SET;
import static com.response.gym.controller.answer.UserAnswers.GIVEN_USER_WAS_NOT_FOUND;
import static com.response.gym.controller.answer.UserAnswers.INVALID_LOGIN_CREDENTIALS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAuthProcessorTest implements BaseUserValidator {

    @InjectMocks
    private UserAuthProcessor userRegistrationProcessor;

    @Mock
    private UserAuthManagement userAuthManagement;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMailRegistrationProcessor userMailRegistrationProcessor;

    @Test
    public void creatingNewAccountWithUniqueData_causesCreatedResponse() {
        //given
        ValidUserRegistrationRequest validUserRegistrationRequest = provideValidUserRegistrationData();
        UserRegistrationResponseDto userRegistrationResponseDto = mapValidUserRegistrationDataToResponse();
        VerificationStateToChange verificationStateToChange = new VerificationStateToChange(true);

        when(userRepository.existsByNicknameOrMail(any(String.class), any(String.class)))
                .thenReturn(false);
        when(userAuthManagement.createUserAccount(any(ValidUserRegistrationRequest.class)))
                .thenReturn(userRegistrationResponseDto);
        userMailRegistrationProcessor.persistPendingMail(eq(userRegistrationResponseDto), eq(verificationStateToChange));

        //when
        MMTResponseCreator response = userRegistrationProcessor.createUserAccount(validUserRegistrationRequest);

        //then
        Assertions
                .assertThat(response.getStatusCode())
                .isEqualTo(new Created().getStatusCode());

        verifyUserMailRegistrationProcessorInvocations(1, userRegistrationResponseDto, verificationStateToChange);
    }

    @Test
    public void creatingNewAccountWithNotUniqueData_causesCreatedResponse() {
        //given
        ValidUserRegistrationRequest validUserRegistrationRequest = provideValidUserRegistrationData();

        when(userRepository.existsByNicknameOrMail(any(String.class), any(String.class)))
                .thenReturn(true);
        //when
        MMTResponseCreator response = userRegistrationProcessor.createUserAccount(validUserRegistrationRequest);

        //then
        Assertions
                .assertThat(response.getStatusCode())
                .isEqualTo(new Conflict().getStatusCode());

        verifyUserMailRegistrationProcessorInvocations(0);
    }

    @Test
    public void logInAccount_userCouldNotBeAuthenticated_ForbiddenWasReturned() {
        ValidUserLoginRequest validUserLoginRequest = provideValidUserLoginData();

        when(userAuthManagement.authenticate(any(String.class), any(String.class)))
                .thenReturn(Either.left(INVALID_LOGIN_CREDENTIALS));

        MMTResponseCreator response = userRegistrationProcessor.logInAccount(validUserLoginRequest);

        Assertions
                .assertThat(response.getStatusCode())
                .isEqualTo(new Forbidden().getStatusCode());
    }

    @Test
    public void logInAccount_userCouldNotBeFound_NotFoundWasReturned() {
        ValidUserLoginRequest validUserLoginRequest = provideValidUserLoginData();

        when(userAuthManagement.authenticate(any(String.class), any(String.class)))
                .thenReturn(Either.right(null));
        when(userRepository.findByNickname(eq(validUserLoginRequest.getNickname())))
                .thenReturn(Optional.empty());

        MMTResponseCreator response = userRegistrationProcessor.logInAccount(validUserLoginRequest);

        Assertions
                .assertThat(response.getStatusCode())
                .isEqualTo(new NotFound().getStatusCode());

        verifyUserMailRegistrationProcessorInvocations(0);
    }

    @Test
    public void logInAccount_validData_okStatusAndValidDataWasReturned() {
        ValidUserLoginRequest validUserLoginRequest = provideValidUserLoginData();
        User user = provideUser();
        UserAuthenticationResponse userLoginResponseDto = new UserAuthenticationResponse(user, "mocked token", "refresh token");

        when(userAuthManagement.authenticate(any(String.class), any(String.class)))
                .thenReturn(Either.right(null));
        when(userRepository.findByNickname(eq(validUserLoginRequest.getNickname())))
                .thenReturn(Optional.of(user));
        when(userAuthManagement.generateTokens(any(User.class)))
                .thenReturn(userLoginResponseDto);

        MMTResponseCreator response = userRegistrationProcessor.logInAccount(validUserLoginRequest);

        Assertions
                .assertThat(response.getStatusCode())
                .isEqualTo(new Ok().getStatusCode());

        Assertions
                .assertThat((UserAuthenticationResponse) response.makeResponse().getBody())
                .isEqualTo(userLoginResponseDto);
    }

    @Test
    public void verifyUser_validData_okStatusAndValidDataWasReturned() {
        ValidUserRegisterConfirmation validUserRegisterConfirmation = provideValidUserRegisterConfirmation(true);
        User user = provideUser();
        UserVerificationResponseDto expectedResponse = mapValidUserRegisterConfirmationToResponse(true);

        when(userRepository.findByNickname(eq(nickname)))
                .thenReturn(Optional.ofNullable(user));

        when(userAuthManagement.verifyUserAccount(eq(user), eq(validUserRegisterConfirmation.getVerified())))
                .thenReturn(expectedResponse);

        MMTResponseCreator response = userRegistrationProcessor.verifyUser(validUserRegisterConfirmation);

        Assertions.assertThat(response.getStatusCode())
                .isEqualTo(new Ok().getStatusCode());

        Assertions.assertThat(response.makeResponse().getBody())
                .isEqualTo(expectedResponse);
    }

    @Test
    public void verifyUser_userDoesNotExist_notFoundStatusAndCorrectCodeWereReturned() {
        ValidUserRegisterConfirmation validUserRegisterConfirmation = provideValidUserRegisterConfirmation(true);

        when(userRepository.findByNickname(eq(nickname)))
                .thenReturn(Optional.empty());

        MMTResponseCreator response = userRegistrationProcessor.verifyUser(validUserRegisterConfirmation);

        Assertions.assertThat(response.getStatusCode())
                .isEqualTo(new NotFound().getStatusCode());

        Assertions.assertThat(response.makeResponse().getBody())
                .isEqualTo(new ErrorResponse(GIVEN_USER_WAS_NOT_FOUND));
    }

    @Test
    public void verifyUser_userIsAlreadyVerified_acceptedStatusAndCorrectCodeWereReturned() {
        ValidUserRegisterConfirmation validUserRegisterConfirmation = provideValidUserRegisterConfirmation(true);
        User user = provideUser();
        user.setIsVerified(true);

        when(userRepository.findByNickname(eq(nickname)))
                .thenReturn(Optional.of(user));

        MMTResponseCreator response = userRegistrationProcessor.verifyUser(validUserRegisterConfirmation);

        Assertions.assertThat(response.getStatusCode())
                .isEqualTo(new Accepted().getStatusCode());

        Assertions.assertThat(response.makeResponse().getBody())
                .isEqualTo(new CommonResponse(GIVEN_USER_VERIFICATION_STATE_IS_ALREADY_SET));
    }

    private void verifyUserMailRegistrationProcessorInvocations(
            int times,
            UserRegistrationResponseDto responseDto,
            VerificationStateToChange verificationStateToChange) {
        verify(userMailRegistrationProcessor, times(times))
                .persistPendingMail(eq(responseDto), eq(verificationStateToChange));
    }

    private void verifyUserMailRegistrationProcessorInvocations(int times) {
        verify(userMailRegistrationProcessor, times(times))
                .persistPendingMail(any(), any());
    }
}

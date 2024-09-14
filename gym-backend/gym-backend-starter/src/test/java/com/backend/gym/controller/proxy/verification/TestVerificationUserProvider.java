package com.backend.gym.controller.proxy.verification;

import com.backend.gym.controller.proxy.BaseIntegrationTest;
import com.backend.gym.controller.proxy.DataProvider;
import com.gym.user.registration.controller.request.base.UserRegisterConfirmation;
import com.gym.user.registration.controller.response.UserVerificationResponseDto;
import com.gym.user.registration.model.Role;
import com.gym.user.registration.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class TestVerificationUserProvider extends BaseIntegrationTest implements DataProvider {

    private String nickname;
    private Boolean isVerified;
    private UUID id;
    private Role role;

    @BeforeEach
    public void setUp() {
        this.nickname = getNickName();
        this.role = getRole();
        this.id = UUID.randomUUID();
        this.isVerified = true;
    }

    UserRegisterConfirmation getUserRegisterConfirmation() {
        return UserRegisterConfirmation.builder()
                .nickname(this.nickname)
                .isVerified(this.isVerified)
                .build();
    }

    void assertUserVerification(ResponseEntity response, HttpStatusCode expectedStatusCode, boolean isVerified) {
        User user = userRepository.findByNickname(this.nickname).orElseThrow();

        Assertions
                .assertThat(response.getStatusCode())
                .isEqualTo(expectedStatusCode);

        Assertions
                .assertThat(user.getIsVerified())
                .isEqualTo(isVerified);
    }

    void assertUserVerification(ResponseEntity response, HttpStatusCode expectedStatusCode, boolean isVerified, UserVerificationResponseDto expectedResponse) {
        assertUserVerification(response, expectedStatusCode, isVerified);

        Assertions
                .assertThat(response.getBody())
                .isEqualTo(expectedResponse);
    }

    void assertUserVerification(ResponseEntity response, HttpStatusCode expectedStatusCode, boolean isVerified, int errorCode) {
        assertUserVerification(response, expectedStatusCode, isVerified);

        Assertions
                .assertThat(response.getBody())
                .isEqualTo(errorCode);
    }

    User getUser() {
        return User.builder()
                .id(this.id)
                .mail(getMail())
                .password(getPassword())
                .role(this.role)
                .isLocked(false)
                .isVerified(false)
                .nickname(this.nickname)
                .build();
    }

    void saveUserAccount() {
        User user = getUser();
        userRepository.save(user);
    }

    void saveUserAccount(boolean isVerified) {
        User user = getUser();
        user.setIsVerified(isVerified);
        userRepository.save(user);
    }

    UserVerificationResponseDto getExpectedResponse(boolean expectedVerificationState) {
        return new UserVerificationResponseDto(id.toString(), nickname, role, expectedVerificationState);
    }
}

package com.backend.gym.controller.proxy.login;

import com.backend.gym.controller.proxy.BaseIntegrationTest;
import com.backend.gym.controller.proxy.DataProvider;
import com.gym.user.registration.controller.request.base.UserLoginDto;
import com.gym.user.registration.controller.response.UserAuthenticationResponse;
import com.gym.user.registration.model.Role;
import com.gym.user.registration.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

class TestUserLoginDataProvider extends BaseIntegrationTest implements DataProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Role role;
    private String nickname;
    private String password;

    @BeforeEach
    public void setUp() {
        this.nickname = getNickName();
        this.role = getRole();
        this.password = getPassword();
    }

    protected UserLoginDto provideValidUserLoginData() {
        return UserLoginDto.builder()
                .nickname(this.nickname)
                .password(this.password)
                .build();
    }

    protected User mapUserLoginDtoToUser() {
        return User.builder()
                .id(UUID.randomUUID())
                .mail(getMail())
                .password(this.password)
                .role(this.role)
                .isLocked(false)
                .isVerified(true)
                .nickname(this.nickname)
                .build();
    }

    protected void assertValidUserLogin(ResponseEntity response) {
        UserAuthenticationResponse loginResponseDto = (UserAuthenticationResponse) response.getBody();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(loginResponseDto).isNotNull();
        Assertions.assertThat(loginResponseDto.getToken()).isNotNull();
        Assertions.assertThat(loginResponseDto.getNickname()).isEqualTo(this.nickname);
    }

    protected void assertInvalidUserLogin(ResponseEntity response, HttpStatus status, int code) {
        Integer errorCode = (Integer) response.getBody();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(status);
        Assertions.assertThat(errorCode).isEqualTo(code);
    }

    protected void assertInvalidUserLogin(ResponseEntity response, HttpStatus status) {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(status);
    }

    protected void saveUserAccount() {
        User user = mapUserLoginDtoToUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    protected void saveUserAccountWhichIsUnverified() {
        User user = mapUserLoginDtoToUser();
        user.setIsVerified(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    protected void saveUserAccountWhichIsLocked() {
        User user = mapUserLoginDtoToUser();
        user.setIsLocked(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}

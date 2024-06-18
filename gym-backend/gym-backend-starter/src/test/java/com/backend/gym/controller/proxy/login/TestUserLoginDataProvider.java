package com.backend.gym.controller.proxy.login;

import com.backend.gym.controller.proxy.BaseIntegrationTest;
import com.backend.gym.controller.proxy.DataProvider;
import com.gym.user.registration.controller.request.base.UserLoginDto;
import com.gym.user.registration.controller.response.UserLoginResponseDto;
import com.gym.user.registration.model.Role;
import com.gym.user.registration.model.User;
import com.response.gym.response.MMTResponseCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

class TestUserLoginDataProvider extends BaseIntegrationTest implements DataProvider {

    private final Role role = Role.MEMBER;
    private String nickname;

    @BeforeEach
    public void setUpNickname() {
        this.nickname = getNickName();
    }

    protected UserLoginDto provideValidUserLoginData() {
        return UserLoginDto.builder()
                .mail(getMail())
                .password(getPassword())
                .build();
    }

    protected User mapUserLoginDtoToUser(UserLoginDto userLoginDto) {
        return User.builder()
                .id(UUID.randomUUID())
                .mail(userLoginDto.getMail())
                .password(userLoginDto.getPassword())
                .role(this.role)
                .nickname(this.nickname)
                .build();
    }

    protected void assertValidUserLogin(ResponseEntity response) {
        UserLoginResponseDto loginResponseDto = (UserLoginResponseDto) response.getBody();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(loginResponseDto).isNotNull();
        Assertions.assertThat(loginResponseDto.getToken()).isNotNull();
        Assertions.assertThat(loginResponseDto.getNickname()).isEqualTo(this.nickname);
        Assertions.assertThat(loginResponseDto.getRole()).isEqualTo(this.role);
    }

    protected void assertInvalidUserLogin(ResponseEntity response, HttpStatus status, int code) {
        Integer errorCode = (Integer) response.getBody();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(status);
        Assertions.assertThat(errorCode).isEqualTo(code);
    }
}

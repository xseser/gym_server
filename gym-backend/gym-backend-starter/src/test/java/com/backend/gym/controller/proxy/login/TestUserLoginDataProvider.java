package com.backend.gym.controller.proxy.login;

import com.backend.gym.controller.proxy.DataProvider;
import com.gym.user.registration.controller.request.base.UserLoginDto;
import com.gym.user.registration.controller.response.UserAuthenticationResponse;
import com.gym.user.registration.model.Role;
import com.gym.user.registration.model.User;
import org.assertj.core.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

class TestUserLoginDataProvider implements DataProvider {

    private final Role role;
    private final String nickname;
    private final String password;

    public TestUserLoginDataProvider() {
        this.nickname = getNickName();
        this.password = getPassword();
        this.role = getRole();
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

    protected void saveUserAccount(Function<String, String> passwordEncoderFunction, Consumer<User> saveUserFunction) {
        User user = mapUserLoginDtoToUser();
        String encodedPassword = passwordEncoderFunction.apply(password);
        user.setPassword(encodedPassword);
        saveUserFunction.accept(user);
    }

    protected void saveUserAccount(
            Function<String, String> passwordEncoderFunction,
            Function<User, User> modifyUser,
            Consumer<User> saveUserFunction) {
        User user = mapUserLoginDtoToUser();
        User modifiedUser = modifyUser.apply(user);
        String encodedPassword = passwordEncoderFunction.apply(password);
        modifiedUser.setPassword(encodedPassword);
        saveUserFunction.accept(modifiedUser);
    }
}

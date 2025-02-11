package com.backend.gym.controller.proxy.verification;

import com.backend.gym.controller.proxy.DataProvider;
import com.gym.user.registration.controller.request.base.UserRegisterConfirmation;
import com.gym.user.registration.controller.response.UserVerificationResponseDto;
import com.gym.user.registration.model.Role;
import com.gym.user.registration.model.User;
import com.response.gym.response.types.CommonResponse;
import com.response.gym.response.types.ErrorResponse;
import com.response.gym.response.types.Response;
import lombok.Setter;
import org.assertj.core.api.Assertions;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

public class TestVerificationUserProvider implements DataProvider {

    private final String nickname;
    private final UUID id;
    private final Role role;

    @Setter
    private boolean isVerified;

    public TestVerificationUserProvider() {
        this.nickname = getNickName();
        this.role = getRole();
        this.id = UUID.randomUUID();
        this.isVerified = false;
    }

    UserRegisterConfirmation getUserRegisterConfirmation(boolean isVerified) {
        return UserRegisterConfirmation.builder()
                .nickname(this.nickname)
                .isVerified(isVerified)
                .build();
    }

    UserRegisterConfirmation getUserRegisterConfirmation(String nickname, boolean isVerified) {
        return UserRegisterConfirmation.builder()
                .nickname(nickname)
                .isVerified(isVerified)
                .build();
    }

    void checkStatusCode(ResponseEntity response, HttpStatusCode expectedStatusCode) {
        Assertions
                .assertThat(response.getStatusCode())
                .isEqualTo(expectedStatusCode);
    }

    void checkIfUserVerificationStateIsCorrect(Function<String, User> dbUserGetter, boolean isVerified) {
        User searchedUser = dbUserGetter.apply(nickname);
        Assertions.assertThat(searchedUser.getIsVerified()).isEqualTo(isVerified);
    }

    void checkResponseBody(ResponseEntity response, UserVerificationResponseDto expectedResponse) {
        Assertions
                .assertThat(response.getBody())
                .isEqualTo(expectedResponse);
    }

    void checkCode(ResponseEntity response, Response body) {
        Assertions
                .assertThat(response.getBody())
                .isEqualTo(body);
    }

    private User getUser() {
        return User.builder()
                .id(this.id)
                .mail(getMail())
                .password(getPassword())
                .role(this.role)
                .isLocked(false)
                .isVerified(this.isVerified)
                .nickname(this.nickname)
                .build();
    }

    void saveUserAccount(Consumer<User> saveUserFunction) {
        User user = getUser();
        saveUserFunction.accept(user);
    }

    UserVerificationResponseDto getExpectedResponse(boolean expectedVerificationState) {
        return new UserVerificationResponseDto(id.toString(), nickname, role, expectedVerificationState);
    }
}

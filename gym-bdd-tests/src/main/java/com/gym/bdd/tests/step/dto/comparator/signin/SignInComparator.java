package com.gym.bdd.tests.step.dto.comparator.signin;

import com.gym.bdd.tests.step.dto.comparator.Comparator;
import com.gym.bdd.tests.step.dto.request.signin.UserLoginInRequest;
import com.gym.bdd.tests.step.dto.response.signin.UserLoginResponse;

import java.util.Objects;

public record SignInComparator(String nickname, String token, String refreshToken) implements Comparator {

    public SignInComparator(UserLoginInRequest userLoginInRequest) {
        this(userLoginInRequest.getNickname(), null, null);
    }

    public SignInComparator(UserLoginResponse userLoginResponse) {
        this(userLoginResponse.getNickname(), userLoginResponse.getToken(), userLoginResponse.getRefreshToken());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SignInComparator that = (SignInComparator) o;
        return Objects.equals(nickname, that.nickname);
    }

    @Override
    public void validate() {
        if (this.refreshToken == null) {
            throw new InvalidLoginResponseException("refresh token is null");
        }

        if (this.token == null) {
            throw new InvalidLoginResponseException("token is null");
        }
    }
}

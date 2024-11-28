package com.gym.bdd.tests.action.impl;

import com.gym.bdd.tests.step.dto.request.UserLoginInRequest;
import com.gym.bdd.tests.step.dto.response.UserLoginResponse;
import cyclops.control.Either;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import org.assertj.core.api.Assertions;

import java.util.Optional;

import static com.gym.bdd.tests.http.ClientSpecification.getSpecification;
import static com.gym.bdd.tests.url.UrlManagement.LOGIN_URL;
import static java.net.HttpURLConnection.HTTP_OK;

@Getter
public class SigningInActions {

    private String token;
    private String refreshToken;

    protected void signIn(String nickname, String password, Optional<Integer> code) {
        UserLoginInRequest request = new UserLoginInRequest(nickname, password);

        signIn(request)
                .bipeek(
                        response -> validate(response, nickname),
                        errorCode -> validate(code, errorCode));
    }

    private Either<UserLoginResponse, String> signIn(UserLoginInRequest userLoginInRequest) {
        Response response = getSpecification()
                .when()
                .log().all()
                .contentType(ContentType.JSON)
                .body(userLoginInRequest)
                .when()
                .post(LOGIN_URL)
                .prettyPeek();
        if (response.getStatusCode() == HTTP_OK) {
            return Either.left(response.getBody().as(UserLoginResponse.class));
        }
        return Either.right(response.getBody().asString());
    }

    private void validate(UserLoginResponse response, String nickname) {
        //TODO
        token = response.getToken();
        refreshToken = response.getRefreshToken();
    }

    private void validate(Optional<Integer> expectedErrorCode, String givenErrorCode) {
        expectedErrorCode.ifPresentOrElse(
                code -> Assertions.assertThat(Integer.valueOf(givenErrorCode)).isEqualTo(code),
                () -> Assertions.fail("Invalid error code: " + givenErrorCode)
        );
    }
}

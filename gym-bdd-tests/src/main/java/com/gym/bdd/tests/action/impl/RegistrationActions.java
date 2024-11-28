package com.gym.bdd.tests.action.impl;

import com.gym.bdd.tests.step.dto.comparator.UserRegistrationComparator;
import com.gym.bdd.tests.step.dto.request.UserRegistrationRequest;
import com.gym.bdd.tests.step.dto.response.UserRegistrationResponse;
import cyclops.control.Either;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.util.Optional;

import static com.gym.bdd.tests.http.ClientSpecification.getSpecification;
import static com.gym.bdd.tests.step.dto.comparator.UserRegistrationComparator.map;
import static com.gym.bdd.tests.url.UrlManagement.REGISTRATION_URL;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

public class RegistrationActions {

    protected void provisionUser(String mail, String nickname, String gender, String role, String password, String passwordMatcher, Optional<Integer> code) {
        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest(mail, password, passwordMatcher, nickname, gender);
        code.ifPresentOrElse(
                errorCode -> provisionUser(userRegistrationRequest).peek(it -> validate(errorCode, it)),
                () -> provisionUser(userRegistrationRequest).peekLeft(it -> validate(it, mail, nickname, gender, role)));
    }

    protected void provisionUser(String mail, String nickname, String gender, String role, String password, Optional<Integer> code) {
        provisionUser(mail, nickname, gender, role, password, password, code);
    }

    protected void provisionUser(String mail, String nickname, String gender, String role, String password) {
        provisionUser(mail, nickname, gender, role, password, password, Optional.empty());
    }

    private Either<UserRegistrationResponse, String> provisionUser(UserRegistrationRequest userRegistrationRequest) {
        Response response = getSpecification()
                .when()
                .log().all()
                .contentType(ContentType.JSON)
                .body(userRegistrationRequest)
                .when()
                .post(REGISTRATION_URL)
                .prettyPeek();
        if (response.getStatusCode() == HTTP_OK) {
            return Either.left(response.getBody().as(UserRegistrationResponse.class));
        }
        return Either.right(response.getBody().asString());
    }

    private void validate(UserRegistrationResponse userRegistrationResponse, String mail, String nickname, String gender, String role) {
        Assertions
                .assertThat(map(userRegistrationResponse))
                .isEqualTo(new UserRegistrationComparator(mail, nickname, gender, role));
    }

    private void validate(int expectedErrorCode, String givenErrorCode) {
        Assertions.assertThat(Integer.valueOf(givenErrorCode)).isEqualTo(expectedErrorCode);
    }
}

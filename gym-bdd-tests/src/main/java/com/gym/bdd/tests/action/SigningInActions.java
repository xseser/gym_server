package com.gym.bdd.tests.action;

import com.gym.bdd.tests.step.dto.comparator.UserRegistrationComparator;
import com.gym.bdd.tests.step.dto.request.UserLoginInRequest;
import com.gym.bdd.tests.step.dto.request.UserRegistrationRequest;
import com.gym.bdd.tests.step.dto.response.UserLoginResponse;
import com.gym.bdd.tests.step.dto.response.UserRegistrationResponse;
import cyclops.control.Either;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import static com.gym.bdd.tests.step.dto.comparator.UserRegistrationComparator.map;
import static com.gym.bdd.tests.url.UrlManagement.LOGIN_URL;
import static com.gym.bdd.tests.url.UrlManagement.REGISTRATION_URL;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

public class SigningInActions {

    private Either<UserLoginResponse, String> signIn(UserLoginInRequest userLoginInRequest) {
        Response response = given()
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

    private void validate(UserLoginInRequest userLoginInRequest, String mail, String nickname, String gender, String role) {
        //TODO
    }

    private void validate(int expectedErrorCode, String givenErrorCode) {
        //TOO
    }
}

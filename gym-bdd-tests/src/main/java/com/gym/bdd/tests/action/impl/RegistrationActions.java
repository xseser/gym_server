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
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

public class RegistrationActions extends Actions<UserRegistrationRequest, UserRegistrationResponse, UserRegistrationComparator> {

    private final String role;

    public RegistrationActions(String role) {
        this.role = role;
    }

    @Override
    public Response serverRequest(UserRegistrationRequest request) {
        return getSpecification()
                .when()
                .log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(REGISTRATION_URL)
                .prettyPeek();
    }

    @Override
    public UserRegistrationComparator mapToComparator(UserRegistrationResponse response) {
        return map(response);
    }

    @Override
    public UserRegistrationComparator mapToComparator(UserRegistrationRequest request) {
        return new UserRegistrationComparator(
                request.getMail(),
                request.getNickName(),
                request.getGender(),
                role);
    }

    @Override
    public int getValidResponseCode() {
        return HTTP_CREATED;
    }

    @Override
    public UserRegistrationResponse getBodyFromResponse(Response response) {
        return response.getBody().as(UserRegistrationResponse.class);
    }
}

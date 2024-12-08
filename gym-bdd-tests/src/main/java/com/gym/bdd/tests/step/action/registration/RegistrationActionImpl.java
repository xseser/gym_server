package com.gym.bdd.tests.step.action.registration;

import com.gym.bdd.tests.step.action.Actions;
import com.gym.bdd.tests.step.dto.comparator.registration.UserRegistrationComparator;
import com.gym.bdd.tests.step.dto.request.registration.UserRegistrationRequest;
import com.gym.bdd.tests.step.dto.response.registration.UserRegistrationResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.gym.bdd.tests.http.ClientSpecification.getSpecification;
import static com.gym.bdd.tests.http.url.UrlManagement.REGISTRATION_URL;
import static java.net.HttpURLConnection.HTTP_CREATED;

public class RegistrationActionImpl extends Actions<UserRegistrationRequest, UserRegistrationResponse, UserRegistrationComparator> {

    private final String role;

    public RegistrationActionImpl(String role) {
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
        return new UserRegistrationComparator(response);
    }

    @Override
    public UserRegistrationComparator mapToComparator(UserRegistrationRequest request) {
        return new UserRegistrationComparator(request, role);
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

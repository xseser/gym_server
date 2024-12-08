package com.gym.bdd.tests.step.action.signin;

import com.gym.bdd.tests.step.action.Actions;
import com.gym.bdd.tests.step.dto.comparator.signin.SignInComparator;
import com.gym.bdd.tests.step.dto.request.signin.UserLoginInRequest;
import com.gym.bdd.tests.step.dto.response.signin.UserLoginResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;

import static com.gym.bdd.tests.http.ClientSpecification.getSpecification;
import static com.gym.bdd.tests.http.url.UrlManagement.LOGIN_URL;
import static java.net.HttpURLConnection.HTTP_OK;

@Getter
public class LoginActionImpl extends Actions<UserLoginInRequest, UserLoginResponse, SignInComparator> {

    private String token;
    private String refreshToken;

    @Override
    public Response serverRequest(UserLoginInRequest request) {
        return getSpecification()
                .when()
                .log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(LOGIN_URL)
                .prettyPeek();
    }

    @Override
    public SignInComparator mapToComparator(UserLoginResponse response) {
        return new SignInComparator(response);
    }

    @Override
    public SignInComparator mapToComparator(UserLoginInRequest request) {
        return new SignInComparator(request);
    }

    @Override
    public int getValidResponseCode() {
        return HTTP_OK;
    }

    @Override
    public UserLoginResponse getBodyFromResponse(Response response) {
        UserLoginResponse userLoginResponse = response.getBody().as(UserLoginResponse.class);
        this.token = userLoginResponse.getToken();
        this.refreshToken = userLoginResponse.getRefreshToken();
        return userLoginResponse;
    }
}

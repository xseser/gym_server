package com.gym.bdd.tests.step.action.confirmation;

import com.gym.bdd.tests.step.action.Actions;
import com.gym.bdd.tests.step.dto.comparator.confirmation.InvalidPullNotificationResponseException;
import com.gym.bdd.tests.step.dto.comparator.confirmation.PullConfirmationComparator;
import com.gym.bdd.tests.step.dto.request.confirmation.PullMailRequest;
import com.gym.bdd.tests.step.dto.request.confirmation.Secret;
import com.gym.bdd.tests.step.dto.response.confirmation.PullConfirmationResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.gym.bdd.tests.http.ClientSpecification.getSpecification;
import static com.gym.bdd.tests.http.url.UrlManagement.PULL_CONFIRMATIONS_URL;
import static java.net.HttpURLConnection.HTTP_OK;

public class MailDeliveryActionsImpl extends Actions<PullMailRequest, PullConfirmationResponse, PullConfirmationComparator> {

    private static final String NICKNAME = "nickname";
    private String secret;

    @Override
    public Response serverRequest(PullMailRequest request) {
        return getSpecification()
                .when()
                .log().all()
                .contentType(ContentType.JSON)
                .queryParam(NICKNAME, request.nickname())
                .when()
                .get(PULL_CONFIRMATIONS_URL)
                .prettyPeek();
    }

    @Override
    public PullConfirmationComparator mapToComparator(PullConfirmationResponse response) {
        return new PullConfirmationComparator(response);
    }

    @Override
    public PullConfirmationComparator mapToComparator(PullMailRequest request) {
        return new PullConfirmationComparator(request);
    }

    @Override
    public int getValidResponseCode() {
        return HTTP_OK;
    }

    @Override
    public PullConfirmationResponse getBodyFromResponse(Response response) {
        PullConfirmationResponse pullConfirmationResponse = response.getBody()
                .jsonPath()
                .getList("$", PullConfirmationResponse.class)
                .stream()
                .filter(it -> it.getLinkType().equals("REGISTRATION"))
                .findFirst()
                .orElseThrow(() -> new InvalidPullNotificationResponseException("Did not find registration link"));
        this.secret = pullConfirmationResponse.getSecret();
        return pullConfirmationResponse;
    }

    public Secret getSecret() {
        return new Secret(secret);
    }
}

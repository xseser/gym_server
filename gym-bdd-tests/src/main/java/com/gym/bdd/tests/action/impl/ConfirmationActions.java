package com.gym.bdd.tests.action.impl;

import com.gym.bdd.tests.step.dto.response.mail.PullConfirmationResponse;
import cyclops.control.Either;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.awaitility.Awaitility;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static com.gym.bdd.tests.http.ClientSpecification.getSpecification;
import static com.gym.bdd.tests.url.UrlManagement.CONFIRMATION_URL;
import static com.gym.bdd.tests.url.UrlManagement.PULL_CONFIRMATIONS_URL;
import static java.net.HttpURLConnection.HTTP_OK;

public class ConfirmationActions {

    private static final String NICKNAME = "nickname";
    private String secret;

    public void confirm(String nickname) {
        waitUntilMailWillBeDeliveredToClient(nickname);
        confirm();
    }

    private Either<Void, String> confirm() {
        Response response = getSpecification()
                .when()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .post(CONFIRMATION_URL + "/" + secret)
                .prettyPeek();
        if (response.getStatusCode() != HTTP_OK) {
            return Either.right(response.getBody().asString());
        }
        return Either.left(null);
    }

    private List<PullConfirmationResponse> pullConfirmations(String nickname) {
        return getSpecification()
                .when()
                .log().all()
                .contentType(ContentType.JSON)
                .queryParam(NICKNAME, nickname)
                .when()
                .get(PULL_CONFIRMATIONS_URL)
                .prettyPeek()
                .getBody()
                .jsonPath()
                .getList("$", PullConfirmationResponse.class);
    }

    private void waitUntilMailWillBeDeliveredToClient(String nickname) {
        Awaitility.await()
                .atMost(Duration.of(15, ChronoUnit.SECONDS))
                .pollInterval(Duration.of(1, ChronoUnit.SECONDS))
                .until(() -> isMailBroadcast(nickname));
    }

    private boolean isMailBroadcast(String nickname) {
        List<PullConfirmationResponse> confirmations = pullConfirmations(nickname);
        if (confirmations.isEmpty()) {
            return false;
        }
        extractRegistrationSecret(confirmations);
        return true;
    }

    private void extractRegistrationSecret(List<PullConfirmationResponse> pullConfirmationResponse) {
        pullConfirmationResponse
                .stream()
                .filter(it -> Objects.equals(it.getLinkType(), "REGISTRATION"))
                .findFirst()
                .ifPresent(mail -> this.secret = mail.getSecret());
    }
}

package com.gym.bdd.tests.step.action.confirmation;

import com.gym.bdd.tests.step.action.Actions;
import com.gym.bdd.tests.step.dto.comparator.confirmation.MailConfirmationComparator;
import com.gym.bdd.tests.step.dto.request.confirmation.Secret;
import com.gym.bdd.tests.step.dto.response.confirmation.MailConfirmationResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.gym.bdd.tests.http.ClientSpecification.getSpecification;
import static com.gym.bdd.tests.http.url.UrlManagement.CONFIRMATION_URL;
import static java.net.HttpURLConnection.HTTP_OK;

public class MailConfirmationActionsImpl extends Actions<Secret, MailConfirmationResponse, MailConfirmationComparator> {

    @Override
    public Response serverRequest(Secret request) {
        return getSpecification()
                .when()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .post(CONFIRMATION_URL, request.secret())
                .prettyPeek();
    }

    @Override
    public MailConfirmationComparator mapToComparator(MailConfirmationResponse response) {
        return new MailConfirmationComparator();
    }

    @Override
    public MailConfirmationComparator mapToComparator(Secret request) {
        return new MailConfirmationComparator();
    }

    @Override
    public int getValidResponseCode() {
        return HTTP_OK;
    }

    @Override
    public MailConfirmationResponse getBodyFromResponse(Response response) {
        return null;
    }
}

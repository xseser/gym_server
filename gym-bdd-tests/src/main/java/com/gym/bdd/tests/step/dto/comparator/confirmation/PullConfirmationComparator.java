package com.gym.bdd.tests.step.dto.comparator.confirmation;

import com.gym.bdd.tests.step.dto.comparator.Comparator;
import com.gym.bdd.tests.step.dto.request.confirmation.PullMailRequest;
import com.gym.bdd.tests.step.dto.response.confirmation.PullConfirmationResponse;

import java.util.Objects;

public record PullConfirmationComparator(
        String id,
        String nickname,
        String mail,
        String secret,
        String expirationLinkTime,
        String linkType,
        String emailStatus) implements Comparator {

    public PullConfirmationComparator(PullMailRequest pullMailRequest) {
        this(
                null,
                pullMailRequest.nickname(),
                null,
                null,
                null,
                null,
                null);
    }

    public PullConfirmationComparator(PullConfirmationResponse response) {
        this(
                response.getId(),
                response.getNickname(),
                response.getMail(),
                response.getSecret(),
                response.getExpirationLinkTime(),
                response.getLinkType(),
                response.getEmailStatus());
    }

    @Override
    public void validate() {
        validateField(id, "id");
        validateField(mail, "mail");
        validateField(secret, "secret");
        validateField(expirationLinkTime, "expirationLinkTime");
        validateField(linkType, "linkType");
        validateField(emailStatus, "emailStatus");
    }

    private void validateField(String field, String fieldName) {
        if (field == null || field.isEmpty()) {
            throw new InvalidPullNotificationResponseException("invalid " + fieldName + " property");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PullConfirmationComparator that = (PullConfirmationComparator) o;
        return Objects.equals(nickname, that.nickname);
    }
}


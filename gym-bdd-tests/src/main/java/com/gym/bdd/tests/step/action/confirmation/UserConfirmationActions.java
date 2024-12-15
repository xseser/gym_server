package com.gym.bdd.tests.step.action.confirmation;

import com.gym.bdd.tests.step.action.ActionsMarker;
import com.gym.bdd.tests.step.dto.comparator.confirmation.InvalidPullNotificationResponseException;
import com.gym.bdd.tests.step.dto.error.ErrorDto;
import com.gym.bdd.tests.step.dto.request.confirmation.PullMailRequest;
import com.gym.bdd.tests.step.dto.request.confirmation.Secret;
import com.gym.bdd.tests.utils.model.AtMost;
import com.gym.bdd.tests.utils.model.PollInterval;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.gym.bdd.tests.utils.Awaiter.awaitFor;

public class UserConfirmationActions implements ActionsMarker {

    private final String nickname;
    private Secret secret;

    public UserConfirmationActions(String nickname) {
        this.nickname = nickname;
    }

    private boolean pullMailsAndCheckIfSecretIsPresent(String nickname) {
        MailDeliveryActionsImpl mailDeliveryActions = new MailDeliveryActionsImpl();
        try {
            mailDeliveryActions.makeAction(new PullMailRequest(nickname), null);
        } catch (InvalidPullNotificationResponseException invalidPullNotificationResponseException) {
            return false;
        }
        this.secret = mailDeliveryActions.getSecret();
        return true;
    }

    private void pullMailsUntilSecretWillNotBeRetrieve(String nickname) {
        awaitFor(
                new AtMost(30, TimeUnit.SECONDS),
                new PollInterval(100, TimeUnit.MILLISECONDS),
                () -> pullMailsAndCheckIfSecretIsPresent(nickname));
    }

    private void confirmPersonalisationAction(Secret secret) {
        new MailConfirmationActionsImpl().makeAction(secret, null);
    }

    private void confirmPersonalisationAction(Secret secret, int code) {
        new MailConfirmationActionsImpl().makeAction(secret, new ErrorDto(code));
    }

    public void confirmPersonalisation() {
        pullMailsUntilSecretWillNotBeRetrieve(nickname);
        confirmPersonalisationAction(secret);
    }

    public void confirmWithInvalidLink(int code) {
        confirmPersonalisationAction(new Secret("invalidSecretValue"), code);
    }
}

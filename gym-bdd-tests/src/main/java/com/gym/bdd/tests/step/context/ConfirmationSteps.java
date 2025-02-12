package com.gym.bdd.tests.step.context;

import com.gym.bdd.tests.step.action.confirmation.UserConfirmationActions;
import com.gym.bdd.tests.init.serenity.SystemHolder;
import com.gym.bdd.tests.step.actor.User;

import java.util.function.Consumer;

public class ConfirmationSteps extends AbstractContext<UserConfirmationActions> {

    @Override
    UserConfirmationActions getAction(User user) {
        return user.getUserConfirmationActions();
    }

    public void givenUserConfirmsLink(String userOrder) {
        doInUserContext(userOrder, UserConfirmationActions::confirmPersonalisation);
    }

    public void givenUserConfirmsInvalidLink(String userOrder, int code) {
        doInUserContext(userOrder, user -> user.confirmWithInvalidLink(code));
    }

    public void givenUserConfirmsLinkTwice(String userOrder, int code) {
        doInUserContext(userOrder, user -> user.confirmPersonalisationTwice(code));
    }
}

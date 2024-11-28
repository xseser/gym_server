package com.gym.bdd.tests.step;

import com.gym.bdd.tests.action.UserConfirmationActions;

import java.util.function.Consumer;

import static com.gym.bdd.tests.init.serenity.cucumber.Holder.getSystemHolder;

public class ConfirmationSteps {

    private void doInUserContext(String userOrder, Consumer<UserConfirmationActions> userAction) {
        userAction.accept(getSystemHolder().getUser(userOrder).getUserConfirmationActions());
    }

    public void givenUserConfirmsLink(String userOrder) {
        doInUserContext(userOrder, UserConfirmationActions::confirmPersonalisation);
    }
}

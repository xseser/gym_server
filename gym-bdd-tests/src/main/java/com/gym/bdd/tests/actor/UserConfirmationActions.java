package com.gym.bdd.tests.actor;

import com.gym.bdd.tests.action.ConfirmationActions;

public class UserConfirmationActions extends ConfirmationActions {

    private final String nickname;

    public UserConfirmationActions(String nickname) {
        this.nickname = nickname;
    }

    public void confirmPersonalisation() {
        confirm(nickname);
    }
}

package com.gym.bdd.tests.action;

import com.gym.bdd.tests.action.impl.ConfirmationActions;

public class UserConfirmationActions extends ConfirmationActions {

    private final String nickname;

    public UserConfirmationActions(String nickname) {
        this.nickname = nickname;
    }

    public void confirmPersonalisation() {
        confirm(nickname);
    }
}

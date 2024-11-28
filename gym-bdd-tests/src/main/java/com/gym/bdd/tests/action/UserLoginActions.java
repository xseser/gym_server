package com.gym.bdd.tests.action;

import com.gym.bdd.tests.action.impl.SigningInActions;

import java.util.Optional;

public class UserLoginActions extends SigningInActions {

    private final String nickname;
    private final String password;

    public UserLoginActions(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public void signInUser() {
        signIn(nickname, password, Optional.empty());
    }
}

package com.gym.bdd.tests.actor.proxy.actions;

import com.gym.bdd.tests.action.UserLoginActions;
import com.gym.bdd.tests.action.UserConfirmationActions;
import com.gym.bdd.tests.action.UserRegistrationActions;
import lombok.Getter;

@Getter
public class UserActions {

    private final UserRegistrationActions userRegistrationActions;
    private final UserLoginActions userLoginActions;
    private final UserConfirmationActions userConfirmationActions;

    public UserActions(String nickname, String password, String mail, String gender, String role) {
        this.userRegistrationActions = new UserRegistrationActions(nickname, password, mail, gender, role);
        this.userLoginActions = new UserLoginActions(nickname, password);
        this.userConfirmationActions = new UserConfirmationActions(nickname);
    }
}

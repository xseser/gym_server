package com.gym.bdd.tests.actor;

import com.gym.bdd.tests.action.RegistrationActions;
import lombok.Getter;

@Getter
public class UserActions extends RegistrationActions {

    private final UserRegistrationActions userRegistrationActions;
    private final UserLoginActions userLoginActions;
    private final UserConfirmationActions userConfirmationActions;

    public UserActions(String nickname, String password, String mail, String gender, String role) {
        this.userRegistrationActions = new UserRegistrationActions(nickname, password, mail, gender, role);
        this.userLoginActions = new UserLoginActions(nickname, password);
        this.userConfirmationActions = new UserConfirmationActions(nickname);
    }
}

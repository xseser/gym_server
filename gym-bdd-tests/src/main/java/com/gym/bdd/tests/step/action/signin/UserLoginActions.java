package com.gym.bdd.tests.step.action.signin;

import com.gym.bdd.tests.step.action.ActionsMarker;
import com.gym.bdd.tests.step.dto.error.ErrorDto;
import com.gym.bdd.tests.step.dto.request.signin.UserLoginInRequest;

public class UserLoginActions implements ActionsMarker {

    private final String nickname;
    private final String password;

    public UserLoginActions(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    private void provideRequestAndAct(String nickname, String password, int code) {
        new LoginActionImpl().makeAction(
                new UserLoginInRequest(nickname, password),
                new ErrorDto(code));
    }

    private void provideRequestAndAct(String nickname, String password) {
        new LoginActionImpl().makeAction(
                new UserLoginInRequest(nickname, password),
                null);
    }

    public void signInUser() {
        provideRequestAndAct(nickname, password);
    }

    public void signInUser(int code) {
        provideRequestAndAct(nickname, password, code);
    }
}

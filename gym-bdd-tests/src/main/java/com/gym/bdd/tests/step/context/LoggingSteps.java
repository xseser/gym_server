package com.gym.bdd.tests.step.context;

import com.gym.bdd.tests.step.action.signin.UserLoginActions;
import com.gym.bdd.tests.init.serenity.SystemHolder;
import com.gym.bdd.tests.step.actor.User;

import java.util.function.Consumer;

public class LoggingSteps extends AbstractContext<UserLoginActions> {

    @Override
    UserLoginActions getAction(User user) {
        return user.getUserLoginActions();
    }

    public void givenUserLoggsIn(String userOrder) {
        doInUserContext(userOrder, UserLoginActions::signInUser);
    }

    public void givenUserTriesToLogIn(String userOrder, int code) {
        doInUserContext(userOrder, user -> user.signInUser(code));
    }
}

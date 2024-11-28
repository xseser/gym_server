package com.gym.bdd.tests.step;

import com.gym.bdd.tests.action.UserLoginActions;

import java.util.function.Consumer;

import static com.gym.bdd.tests.init.serenity.cucumber.Holder.getSystemHolder;

public class LoggingSteps {

    private void doInUserContext(String userOrder, Consumer<UserLoginActions> userAction) {
        userAction.accept(getSystemHolder().getUser(userOrder).getUserLoginActions());
    }

    public void givenUserLoggsIn(String userOrder) {
        doInUserContext(userOrder, UserLoginActions::signInUser);
    }
 }

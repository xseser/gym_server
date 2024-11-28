package com.gym.bdd.tests.step;

import com.gym.bdd.tests.actor.User;
import com.gym.bdd.tests.action.UserRegistrationActions;
import io.qameta.allure.Step;

import java.util.function.Consumer;

import static com.gym.bdd.tests.init.serenity.cucumber.Holder.getSystemHolder;

public class RegistrationSteps {

    public void createUserData(String userOrder) {
        new User(userOrder);
    }

    private void doInUserContext(String userOrder, Consumer<UserRegistrationActions> userAction) {
        userAction.accept(getSystemHolder().getUser(userOrder).getUserRegistrationActions());
    }

    @Step
    public void provisionUser(String userOrder) {
        doInUserContext(userOrder, UserRegistrationActions::provisionUser);
    }

    public void provisionUser(String userOrder, int errorCode) {
        doInUserContext(userOrder, user -> user.provisionUser(errorCode));
    }

    public void provisionUserWithGender(String userOrder) {
        doInUserContext(userOrder, UserRegistrationActions::provisionUserWithGender);
    }

    public void provisionUserWithGender(String userOrder, int errorCode) {
        doInUserContext(userOrder, user -> user.provisionUserWithGender(errorCode));
    }

    public void provisionUserWithoutName(String userOrder, int errorCode) {
        doInUserContext(userOrder, user -> user.provisionUserWithoutNickname(errorCode));
    }

    public void provisionUserMailName(String userOrder, int errorCode) {
        doInUserContext(userOrder, user -> user.provisionUserWithoutMail(errorCode));
    }

    public void provisionUserWithDifferentPasswords(String userOrder, int errorCode) {
        doInUserContext(userOrder, user -> user.provisionUserWithDifferentPasswords(errorCode));
    }

    public void provisionUserWithIncorrectPassword(String userOrder, int errorCode) {
        doInUserContext(userOrder, user -> user.provisionUserWithIncorrectPassword(errorCode));
    }

    public void provisionUserWithIncorrectGender(String userOrder, int errorCode) {
        doInUserContext(userOrder, user -> user.provisionUserWithIncorrectGender(errorCode));
    }
}

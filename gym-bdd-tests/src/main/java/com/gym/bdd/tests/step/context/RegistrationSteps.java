package com.gym.bdd.tests.step.context;

import com.gym.bdd.tests.step.actor.User;
import com.gym.bdd.tests.step.action.registration.UserRegistrationActions;

public class RegistrationSteps extends AbstractContext<UserRegistrationActions> {

    @Override
    UserRegistrationActions getAction(User user) {
        return user.getUserRegistrationActions();
    }

    public void createUserData(String userOrder) {
        new User(userOrder);
    }

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

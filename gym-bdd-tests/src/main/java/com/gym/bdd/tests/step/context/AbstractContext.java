package com.gym.bdd.tests.step.context;

import com.gym.bdd.tests.init.serenity.SystemHolder;
import com.gym.bdd.tests.step.action.ActionsMarker;
import com.gym.bdd.tests.step.actor.User;

import java.util.function.Consumer;

public abstract class AbstractContext<T extends ActionsMarker> {

    void doInUserContext(String userOrder, Consumer<T> action) {
        action.accept(getAction(SystemHolder.getSystemHolder().getUser(userOrder)));
    }

    abstract T getAction(User user);
}

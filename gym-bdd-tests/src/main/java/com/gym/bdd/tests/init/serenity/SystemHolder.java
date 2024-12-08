package com.gym.bdd.tests.init.serenity;

import com.gym.bdd.tests.step.actor.User;

import java.util.HashMap;
import java.util.Map;

public class SystemHolder {

    private final Map<String, User> users = new HashMap<>();
    private static SystemHolder singleton;

    private SystemHolder() {}

    public static SystemHolder getSystemHolder() {
        if (singleton == null) {
            singleton = new SystemHolder();
        }
        return singleton;
    }

    public void setUser(String userOrder, User user) {
        this.users.put(userOrder, user);
    }

    public User getUser(String userOrder) {
        return this.users.get(userOrder);
    }
}

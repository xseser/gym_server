package com.gym.bdd.tests.serenity;

import com.gym.bdd.tests.actor.User;

import java.util.HashMap;
import java.util.Map;

public class SystemHolder {

    Map<String, User> users = new HashMap<>();

    public void setUser(String userOrder, User user) {
        this.users.put(userOrder, user);
    }

    public User getUser(String userOrder) {
        return this.users.get(userOrder);
    }
}

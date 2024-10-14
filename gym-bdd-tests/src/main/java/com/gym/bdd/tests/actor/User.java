package com.gym.bdd.tests.actor;

import static com.gym.bdd.tests.serenity.cucumber.Holder.getSystemHolder;

public class User extends UserActions {

    private String token;

    public User(String userOrder) {
        getSystemHolder().setUser(userOrder, this);
    }

    public void setToken(String token) {
        this.token = token;
    }
}

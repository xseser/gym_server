package com.gym.bdd.tests.cucumber;

import com.gym.bdd.tests.serenity.SystemHolder;
import io.cucumber.java.Before;

import static com.gym.bdd.tests.serenity.cucumber.Holder.initialiseSystemHolder;

public class Hook {

    @Before(order = 0)
    public void setUpCucumberHolders() {
        SystemHolder systemHolder = new SystemHolder();
        initialiseSystemHolder(systemHolder);
    }
}

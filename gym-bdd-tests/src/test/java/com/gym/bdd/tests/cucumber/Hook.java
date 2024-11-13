package com.gym.bdd.tests.cucumber;

import com.gym.bdd.tests.actor.Admin;
import com.gym.bdd.tests.init.serenity.SystemHolder;
import io.cucumber.java.Before;

import java.util.Optional;

import static com.gym.bdd.tests.init.serenity.cucumber.Holder.getSystemHolder;
import static com.gym.bdd.tests.init.serenity.cucumber.Holder.initialiseSystemHolder;
import static com.gym.bdd.tests.property.PropertyReader.ADMIN_HOLDER;

public class Hook {

    @Before(order = 0)
    public void setUpCucumberHolders() {
        SystemHolder systemHolder = new SystemHolder();
        initialiseSystemHolder(systemHolder);
    }

    @Before(order = 1)
    public void setUpAdminContext() {
        Optional.ofNullable(getSystemHolder().getUser(ADMIN_HOLDER))
                .ifPresentOrElse(
                        existingAdmin -> {},
                        () -> new Admin(ADMIN_HOLDER).getUserLoginActions().signInUser());
    }
}

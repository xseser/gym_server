package com.gym.bdd.tests.step.def;

import com.gym.bdd.tests.step.LoggingSteps;
import io.cucumber.java.en.Given;

public class UserLoggingDefSteps {

    private final LoggingSteps loggingSteps;

    public UserLoggingDefSteps() {
        this.loggingSteps = new LoggingSteps();
    }

    @Given("user uses {string} user valid login data and loggs in")
    public void userUsesFIRSTUserValidLoginDataAndLoggsIn(String userOrder) {
        loggingSteps.givenUserLoggsIn(userOrder);
    }
}

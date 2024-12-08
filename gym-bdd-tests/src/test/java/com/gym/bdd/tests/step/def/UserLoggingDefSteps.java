package com.gym.bdd.tests.step.def;

import com.gym.bdd.tests.step.context.LoggingSteps;
import io.cucumber.java.en.When;

public class UserLoggingDefSteps {

    private final LoggingSteps loggingSteps;

    public UserLoggingDefSteps() {
        this.loggingSteps = new LoggingSteps();
    }

    @When("user uses {string} user valid login data and loggs in")
    public void userUsesFIRSTUserValidLoginDataAndLoggsIn(String userOrder) {
        loggingSteps.givenUserLoggsIn(userOrder);
    }

    @When("{string} not confirmed user tries to log in and receive <{int}> error code")
    public void notConfirmedUserTriesToSignIn(String userOrder, int code) {
        loggingSteps.givenUserTriesToLogIn(userOrder, code);
    }

}

package com.gym.bdd.tests.step.def;

import com.gym.bdd.tests.step.ConfirmationSteps;
import io.cucumber.java.en.When;

public class UserEmailConfirmationDefSteps {

    private ConfirmationSteps confirmationSteps;

    public UserEmailConfirmationDefSteps() {
        confirmationSteps = new ConfirmationSteps();
    }

    @When("{string} user confirms email")
    public void userConfirmsEmail(String userOrder) {
        confirmationSteps.givenUserConfirmsLink(userOrder);
    }
}

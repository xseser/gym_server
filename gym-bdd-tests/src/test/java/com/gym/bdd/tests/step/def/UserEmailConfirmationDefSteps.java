package com.gym.bdd.tests.step.def;

import com.gym.bdd.tests.step.context.ConfirmationSteps;
import io.cucumber.java.en.When;

public class UserEmailConfirmationDefSteps {

    private final ConfirmationSteps confirmationSteps;

    public UserEmailConfirmationDefSteps() {
        confirmationSteps = new ConfirmationSteps();
    }

    @When("{string} user confirms email")
    public void userConfirmsEmail(String userOrder) {
        confirmationSteps.givenUserConfirmsLink(userOrder);
    }

    @When("{string} user confirms his email with invalid secret and receives <{int}> code")
    public void userConfirmsWithInvalidData(String userOrder, int code) {
        confirmationSteps.givenUserConfirmsInvalidLink(userOrder, code);
    }
}

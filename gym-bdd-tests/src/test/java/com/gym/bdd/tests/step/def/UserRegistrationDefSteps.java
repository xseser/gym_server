package com.gym.bdd.tests.step.def;

import com.gym.bdd.tests.step.context.RegistrationSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class UserRegistrationDefSteps {

    private final RegistrationSteps registrationSteps;

    public UserRegistrationDefSteps() {
        this.registrationSteps = new RegistrationSteps();
    }

    @Given("valid {string} user registration data")
    public void validFIRSTUserRegistrationData(String userOrder) {
        registrationSteps.createUserData(userOrder);
    }

    @When("{string} user registers with valid data with gender")
    public void firstUserRegistersWithValidDataWithGender(String userOrder) {
        registrationSteps.provisionUserWithGender(userOrder);
    }

    @When("{string} user registers with valid data without nickname and receives <{int}> error code")
    public void firstUserRegistersWithValidDataWithoutNicknameAndReceivesErrorCode(String userOrder, int code) {
        registrationSteps.provisionUserWithoutName(userOrder, code);
    }

    @When("{string} user registers with valid data without mail and receives <{int}> error code")
    public void firstUserRegistersWithValidDataWithoutMailAndReceivesErrorCode(String userOrder, int code) {
        registrationSteps.provisionUserMailName(userOrder, code);
    }

    @When("{string} user registers with valid data without gender")
    public void firstUserRegistersWithValidDataWithoutGender(String userOrder) {
        registrationSteps.provisionUser(userOrder);
    }

    @And("{string} user registers with valid data with gender and receives <{int}> error code")
    public void firstUserRegistersWithValidDataWithGenderAndReceivesErrorCode(String userOrder, int code) {
        registrationSteps.provisionUserWithGender(userOrder, code);
    }

    @And("{string} user registers with valid data without gender and receives <{int}> error code")
    public void firstUserRegistersWithValidDataWithoutGenderAndReceivesErrorCode(String userOrder, int code) {
        registrationSteps.provisionUser(userOrder, code);
    }

    @When("{string} user registers with different passwords and receives <{int}> error code")
    public void firstUserRegistersWithDifferentPasswordsAndReceivesErrorCode(String userOrder, int code) {
        registrationSteps.provisionUserWithDifferentPasswords(userOrder, code);
    }

    @When("{string} user registers with invalid password and receives <{int}> error code")
    public void firstUserRegistersWithInvalidPasswordAndReceivesErrorCode(String userOrder, int code) {
        registrationSteps.provisionUserWithIncorrectPassword(userOrder, code);
    }

    @When("{string} user registers with invalid gender and receives <{int}> error code")
    public void firstUserRegistersWithInvalidGenderAndReceivesErrorCode(String userOrder, int code) {
        registrationSteps.provisionUserWithIncorrectGender(userOrder, code);
    }

    @Given("{string} user registers with random data")
    public void firstUserRegistersWithRandomData(String userOrder) {
        registrationSteps.createUserData(userOrder);
        registrationSteps.provisionUser(userOrder);
    }
}

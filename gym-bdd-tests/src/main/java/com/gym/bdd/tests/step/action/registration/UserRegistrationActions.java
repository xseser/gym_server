package com.gym.bdd.tests.step.action.registration;

import com.gym.bdd.tests.step.action.ActionsMarker;
import com.gym.bdd.tests.step.dto.error.ErrorDto;
import com.gym.bdd.tests.step.dto.request.registration.UserRegistrationRequest;
import io.qameta.allure.Step;

import static com.gym.bdd.tests.data.generator.DataGenerator.generatePassword;

public class UserRegistrationActions implements ActionsMarker {

    private final String nickname;
    private final String password;
    private final String mail;
    private final String gender;
    private final String role;

    public UserRegistrationActions(String nickname, String password, String mail, String gender, String role) {
        this.nickname = nickname;
        this.password = password;
        this.mail = mail;
        this.gender = gender;
        this.role = role;
    }

    private void provideRequestAndAct(String mail, String nickname, String gender, String passwordMatcher, String password) {
        new RegistrationActionImpl(role).makeAction(
                new UserRegistrationRequest(mail, password, passwordMatcher, nickname, gender),
                null);
    }

    private void provideRequestAndAct(String mail, String nickname, String gender, String passwordMatcher, String password, int code) {
        new RegistrationActionImpl(role).makeAction(
                new UserRegistrationRequest(mail, password, passwordMatcher, nickname, gender),
                new ErrorDto(code));
    }

    @Step
    public void provisionUser() {
        provideRequestAndAct(mail, nickname, null, password, password);
    }

    public void provisionUser(int errorCode) {
        provideRequestAndAct(mail, nickname, null, password, password, errorCode);
    }

    public void provisionUserWithGender() {
        provideRequestAndAct(mail, nickname, gender, password, password);
    }

    public void provisionUserWithGender(int errorCode) {
        provideRequestAndAct(mail, nickname, gender, password, password, errorCode);
    }

    public void provisionUserWithoutNickname(int errorCode) {
        provideRequestAndAct(mail, null, gender, password, password, errorCode);
    }

    public void provisionUserWithoutMail(int errorCode) {
        provideRequestAndAct(null, nickname, gender, password, password, errorCode);
    }

    public void provisionUserWithDifferentPasswords(int errorCode) {
        provideRequestAndAct(mail, nickname, gender, password, generatePassword(), errorCode);
    }

    public void provisionUserWithIncorrectPassword(int errorCode) {
        provideRequestAndAct(mail, nickname, gender, password.toLowerCase(), password.toLowerCase(), errorCode);
    }

    public void provisionUserWithIncorrectGender(int errorCode) {
        provideRequestAndAct(mail, nickname, gender + "aa", password, password, errorCode);
    }
}

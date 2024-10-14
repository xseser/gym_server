package com.gym.bdd.tests.actor;

import com.gym.bdd.tests.action.RegistrationActions;

import java.util.Optional;

import static com.gym.bdd.tests.data.generator.DataGenerator.generatePassword;

public class UserRegistrationActions extends RegistrationActions {

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

    public void provisionUser() {
        provisionUser(mail, nickname, null, role, password);
    }

    public void provisionUser(int errorCode) {
        provisionUser(mail, nickname, null, role, password, Optional.of(errorCode));
    }

    public void provisionUserWithGender() {
        provisionUser(mail, nickname, gender, role, password);
    }

    public void provisionUserWithGender(int errorCode) {
        provisionUser(mail, nickname, gender, role, password, Optional.of(errorCode));
    }

    public void provisionUserWithoutNickname(int errorCode) {
        provisionUser(mail, null, gender, role, password, Optional.of(errorCode));
    }

    public void provisionUserWithoutMail(int errorCode) {
        provisionUser(null, nickname, gender, role, password, Optional.of(errorCode));
    }

    public void provisionUserWithDifferentPasswords(int errorCode) {
        provisionUser(mail, nickname, gender, role, password, generatePassword(), Optional.of(errorCode));
    }

    public void provisionUserWithIncorrectPassword(int errorCode) {
        provisionUser(mail, nickname, gender, role, password.toLowerCase(), Optional.of(errorCode));
    }

    public void provisionUserWithIncorrectGender(int errorCode) {
        provisionUser(mail, nickname, gender + "aa", role, password, Optional.of(errorCode));
    }
}

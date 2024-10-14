package com.gym.bdd.tests.actor;

import com.gym.bdd.tests.action.RegistrationActions;
import lombok.Getter;

import static com.gym.bdd.tests.data.generator.DataGenerator.generateGender;
import static com.gym.bdd.tests.data.generator.DataGenerator.generateMail;
import static com.gym.bdd.tests.data.generator.DataGenerator.generateNickname;
import static com.gym.bdd.tests.data.generator.DataGenerator.generatePassword;

public class UserActions extends RegistrationActions {

    private final String nickname;
    private final String password;
    private final String mail;
    private final String gender;
    private final String role;

    @Getter
    private final UserRegistrationActions userRegistrationActions;

    @Getter
    private final UserLoginActions userLoginActions;

    public UserActions() {
        this.nickname = generateNickname();
        this.gender = generateGender();
        this.mail = generateMail();
        this.password = generatePassword();
        this.role = "MEMBER";
        this.userRegistrationActions = new UserRegistrationActions(nickname, password, mail, gender, role);
        this.userLoginActions = new UserLoginActions(nickname, password);
    }
}

package com.gym.bdd.tests.actor;

import com.gym.bdd.tests.actor.proxy.actions.UserActions;
import lombok.Getter;

import static com.gym.bdd.tests.data.generator.DataGenerator.generateGender;
import static com.gym.bdd.tests.data.generator.DataGenerator.generateMail;
import static com.gym.bdd.tests.data.generator.DataGenerator.generateNickname;
import static com.gym.bdd.tests.data.generator.DataGenerator.generatePassword;
import static com.gym.bdd.tests.init.serenity.cucumber.Holder.getSystemHolder;

@Getter
public class User extends UserActions {

    private final String nickname;
    private final String password;
    private final String email;

    public User(String userOrder, String nickname, String password, String email, String role, String gender) {
        super(nickname, password, email, gender, role);
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        getSystemHolder().setUser(userOrder, this);
    }

    public User(String userOrder) {
        this(userOrder, generateNickname(), generatePassword(), generateMail(), "MEMBER", generateGender());
    }
}

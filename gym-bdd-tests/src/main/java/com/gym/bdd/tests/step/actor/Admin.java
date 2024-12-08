package com.gym.bdd.tests.step.actor;

import static com.gym.bdd.tests.property.PropertyReader.getAdminGender;
import static com.gym.bdd.tests.property.PropertyReader.getAdminMail;
import static com.gym.bdd.tests.property.PropertyReader.getAdminMember;
import static com.gym.bdd.tests.property.PropertyReader.getAdminNickname;
import static com.gym.bdd.tests.property.PropertyReader.getAdminPassword;

public class Admin extends User {

    private final String nickname;
    private final String password;
    private final String email;
    private final String gender;
    private final String role;

    public Admin(String userOrder, String nickname, String password, String email, String gender, String role) {
        super(userOrder, nickname, password, email, role, gender);
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.role = role;
    }

    public Admin(String userOrder) {
        this(userOrder, getAdminNickname(), getAdminPassword(), getAdminMail(), getAdminGender(), getAdminMember());
    }
}

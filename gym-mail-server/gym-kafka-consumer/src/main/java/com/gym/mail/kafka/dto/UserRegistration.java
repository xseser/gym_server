package com.gym.mail.kafka.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserRegistration extends CommonMailDto {
    private String verification;

    public UserRegistration(String nickname, String mail, String verification) {
        super(nickname, mail);
        this.verification = verification;
    }

    @Override
    public String toString() {
        return "UserRegistration{" +
               "verification='" + verification + '\'' +
               "nickname='" + getNickname() + '\'' +
               "mail='" + getMail() + '\'' +
               '}';
    }
}
package com.gym.user.registration.mail;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CommonMailDto {
    private String nickname;
    private String mail;

    public CommonMailDto(String nickname, String mail) {
        this.nickname = nickname;
        this.mail = mail;
    }
}

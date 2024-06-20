package com.gym.user.registration.controller.request.valid;

public class ValidUserLoginDto {
    private String nickname;
    private String password;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ValidUserLoginDto{" +
               "nickname='" + nickname + '\'' +
               ", password='" + "*******" + '\'' +
               '}';
    }
}

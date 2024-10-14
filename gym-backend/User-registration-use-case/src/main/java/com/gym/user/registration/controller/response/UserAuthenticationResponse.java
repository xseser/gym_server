package com.gym.user.registration.controller.response;

import com.gym.user.registration.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticationResponse {

    private String nickname;
    private String token;
    private String refreshToken;

    public String getNickname() {
        return nickname;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "UserAuthenticationResponse{" +
               "nickname='" + nickname + '\'' +
               ", token='" + "****token****" + '\'' +
               ", refreshToken='" + "****refreshToken****" + '\'' +
               ", refreshToken='" + refreshToken + '\'' +
               '}';
    }

    public UserAuthenticationResponse(User user, String token, String refreshToken) {
        log.info("User account logged in successfully. Logged user data: {}", this);
        this.nickname = user.getNickname();
        this.token = token;
        this.refreshToken = refreshToken;
    }
}

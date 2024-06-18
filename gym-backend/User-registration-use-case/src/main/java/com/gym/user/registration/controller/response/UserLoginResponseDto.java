package com.gym.user.registration.controller.response;

import com.gym.user.registration.model.Role;
import com.gym.user.registration.model.User;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class UserLoginResponseDto {

    private Role role;
    private String nickname;
    private String token;

    public Role getRole() {
        return role;
    }

    public String getNickname() {
        return nickname;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "UserLoginResponseDto{" +
               "role=" + role +
               ", nickname='" + nickname + '\'' +
               ", token='" + "****token****" + '\'' +
               '}';
    }

    public UserLoginResponseDto(User user, String token) {
        log.info("User account logged in successfully. Logged user data: {}", this);
        this.role = user.getRole();
        this.nickname = user.getNickname();
        this.token = token;
    }
}

package com.gym.user.registration.controller.response;

import com.gym.user.registration.model.Gender;
import com.gym.user.registration.model.Role;
import com.gym.user.registration.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@AllArgsConstructor
@Data
@Slf4j
public class UserRegistrationResponseDto {

    private UUID id;
    private String mail;
    private String nickname;
    private Gender gender;
    private Role role;

    public UserRegistrationResponseDto(User user) {
        this.id = user.getId();
        this.mail = user.getMail();
        this.nickname = user.getNickname();
        this.gender = user.getGender();
        this.role = user.getRole();
        log.info("User account created successfully. Provisioned user: {}", this);
    }

    @Override
    public String toString() {
        return "UserRegistrationResponseDto{" +
               "id=" + id +
               ", mail='" + mail + '\'' +
               ", nickname='" + nickname + '\'' +
               ", gender=" + gender +
               ", role=" + role +
               '}';
    }
}

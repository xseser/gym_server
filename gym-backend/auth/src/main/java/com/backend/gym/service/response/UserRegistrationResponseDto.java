package com.backend.gym.service.response;

import com.backend.gym.model.user.Gender;
import com.backend.gym.model.user.Role;
import com.backend.gym.model.user.User;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
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
    }
}

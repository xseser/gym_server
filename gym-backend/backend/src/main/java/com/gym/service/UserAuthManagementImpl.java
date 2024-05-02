package com.gym.service;

import com.gym.common.response.Created;
import com.gym.common.response.MMTResponseCreator;
import com.gym.common.response.Ok;
import com.gym.controller.request.dto.valid.ValidUserLoginDto;
import com.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.gym.model.user.Role;
import com.gym.model.user.User;
import com.gym.repository.UserRepository;
import com.gym.service.validator.RegistrationValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserAuthManagementImpl implements UserAuthManagement {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    public UserAuthManagementImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public MMTResponseCreator createUserAccount(ValidUserRegistrationRequest validUserRegistrationRequest) {
        User user = User.builder()
                .id(UUID.randomUUID())
                .mail(validUserRegistrationRequest.getMail())
                .password(passwordEncoder.encode(validUserRegistrationRequest.getPassword()))
                .nickname(validUserRegistrationRequest.getNickname())
                .gender(validUserRegistrationRequest.getGender())
                .isVerified(false)
                .role(Role.MEMBER)
                .build();
        User provisionedUser = userRepository.save(user);
        return new Created(provisionedUser);
    }

    @Override
    public MMTResponseCreator logInAccount(ValidUserLoginDto validUserLoginDto) {
        //FIXME implement
        return new Ok();
    }
}

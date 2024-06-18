package com.gym.user.registration.service;

import com.gym.user.registration.controller.request.valid.ValidUserRegistrationRequest;
import com.gym.user.registration.controller.response.UserLoginResponseDto;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import com.gym.user.registration.model.Role;
import com.gym.user.registration.model.User;
import com.gym.user.registration.repository.UserRepository;
import gym.mmt.auth.config.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserAuthManagementImpl implements UserAuthManagement {

    private final CustomPasswordEncoder customPasswordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserAuthManagementImpl(CustomPasswordEncoder customPasswordEncoder, UserRepository userRepository, JwtService jwtService) {
        this.customPasswordEncoder = customPasswordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public UserRegistrationResponseDto createUserAccount(ValidUserRegistrationRequest validUserRegistrationRequest) {
        User user = User.builder()
                .id(UUID.randomUUID())
                .mail(validUserRegistrationRequest.getMail())
                .password(customPasswordEncoder.encode(validUserRegistrationRequest.getPassword()))
                .nickname(validUserRegistrationRequest.getNickname())
                .gender(validUserRegistrationRequest.getGender())
                .isVerified(false)
                .isLocked(false)
                .role(Role.MEMBER)
                .build();
        User provisionedUser = userRepository.save(user);
        return new UserRegistrationResponseDto(provisionedUser);
    }

    @Override
    public UserLoginResponseDto logInAccount(User user) {
        String tokenGenerated = jwtService.generateToken(user);
        return new UserLoginResponseDto(user, tokenGenerated);
    }
}

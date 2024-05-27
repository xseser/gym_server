package com.backend.gym.service;

import com.backend.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.backend.gym.service.response.UserLoginResponseDto;
import com.backend.gym.service.response.UserRegistrationResponseDto;
import com.response.gym.response.Created;
import com.response.gym.response.MMTResponseCreator;
import com.response.gym.response.Ok;
import com.backend.gym.model.user.Role;
import com.backend.gym.model.user.User;
import com.backend.gym.repository.UserRepository;
import gym.mmt.auth.config.JwtService;
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
    public MMTResponseCreator createUserAccount(ValidUserRegistrationRequest validUserRegistrationRequest) {
        User user = User.builder()
                .id(UUID.randomUUID())
                .mail(validUserRegistrationRequest.getMail())
                .password(customPasswordEncoder.encode(validUserRegistrationRequest.getPassword()))
                .nickname(validUserRegistrationRequest.getNickname())
                .gender(validUserRegistrationRequest.getGender())
                .isVerified(false)
                .role(Role.MEMBER)
                .build();
        User provisionedUser = userRepository.save(user);
        UserRegistrationResponseDto userRegistrationResponseDto = new UserRegistrationResponseDto(provisionedUser);
        return new Created(userRegistrationResponseDto);
    }

    @Override
    public MMTResponseCreator logInAccount(User user) {
        String tokenGenerated = jwtService.generateToken(user);
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto(user, tokenGenerated);
        return new Ok(userLoginResponseDto);
    }
}

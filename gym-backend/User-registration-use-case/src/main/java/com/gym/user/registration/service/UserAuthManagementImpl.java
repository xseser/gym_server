package com.gym.user.registration.service;

import com.gym.user.registration.controller.request.valid.ValidUserRegistrationRequest;
import com.gym.user.registration.controller.response.UserLoginResponseDto;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import com.gym.user.registration.controller.response.UserVerificationResponseDto;
import com.gym.user.registration.model.Role;
import com.gym.user.registration.model.User;
import com.gym.user.registration.repository.UserRepository;
import com.response.gym.response.BadRequest;
import com.response.gym.response.Conflict;
import com.response.gym.response.InternalServerError;
import com.response.gym.response.MMTResponseCreator;
import com.response.gym.response.NotFound;
import cyclops.control.Either;
import gym.mmt.auth.config.JwtService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.response.gym.controller.answer.UserAnswers.INVALID_LOCK_STATE;
import static com.response.gym.controller.answer.UserAnswers.INVALID_LOGIN_CREDENTIALS;
import static com.response.gym.controller.answer.UserAnswers.INVALID_VERIFICATION_STATE;
import static com.response.gym.controller.answer.UserAnswers.UNKNOWN_ERROR_DURING_AUTHENTICATION;

@Slf4j
@Component
public class UserAuthManagementImpl implements UserAuthManagement {

    private final CustomPasswordEncoder customPasswordEncoder;
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserAuthManagementImpl(CustomPasswordEncoder customPasswordEncoder, AuthenticationManager authenticationManager,
            UserRepository userRepository, JwtService jwtService) {
        this.customPasswordEncoder = customPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Transactional
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

    @Transactional
    @Override
    public UserVerificationResponseDto verifyUserAccount(User user, Boolean isVerified) {
        user.setIsVerified(isVerified);
        userRepository.save(user);
        return new UserVerificationResponseDto(user);
    }

    @Override
    public Either<Integer, Void> authenticate(String nickname, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(nickname, password));
            return Either.right(null);
        } catch (Exception e) {
            log.error(
                    "Error during authenticating user with nickname: {} -> with error: {}",
                    nickname,
                    e.getMessage());
            return Either.left(resolveException(e));
        }
    }

    private Integer resolveException(Throwable throwable) {
        return switch (throwable.getMessage()) {
            case "User is disabled" -> INVALID_VERIFICATION_STATE;
            case "User account is locked" -> INVALID_LOCK_STATE;
            case "Bad credentials" -> INVALID_LOGIN_CREDENTIALS;
            default -> UNKNOWN_ERROR_DURING_AUTHENTICATION;
        };
    }
}

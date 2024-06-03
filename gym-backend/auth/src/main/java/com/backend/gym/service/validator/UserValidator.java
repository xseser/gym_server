package com.backend.gym.service.validator;

import com.backend.gym.controller.request.dto.valid.ValidUserRegistrationRequest;
import com.backend.gym.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserAccountUnique(String mail, String nickname) {
        return userRepository.findByMail(mail)
                .map(user -> false)
                .orElseGet(()-> userRepository.findByNickname(nickname)
                        .isEmpty());
    }

    public boolean arePasswordAndPasswordMatcherMatch(ValidUserRegistrationRequest validUserRegistrationRequest) {
        return Objects.equals(validUserRegistrationRequest.getPassword(), validUserRegistrationRequest.getPasswordMatcher());
    }
}

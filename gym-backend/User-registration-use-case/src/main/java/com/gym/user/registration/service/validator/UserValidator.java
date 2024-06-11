package com.gym.user.registration.service.validator;

import com.gym.user.registration.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserAccountUnique(String mail, String nickname) {
        return userRepository.findByMail(mail)
                .map(user -> false)
                .orElseGet(() -> userRepository.findByNickname(nickname)
                        .isEmpty());
    }
}

package com.gym.service.validator;

import com.gym.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class RegistrationValidator {

    private final UserRepository userRepository;

    public RegistrationValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserAccountUnique(String mail, String nickname) {
        return userRepository.findByMail(mail)
                .map(user -> false)
                .orElseGet(()-> userRepository.findByNickname(nickname)
                        .isEmpty());
    }
}

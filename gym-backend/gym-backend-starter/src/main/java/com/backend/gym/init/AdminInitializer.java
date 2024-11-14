package com.backend.gym.init;

import com.gym.user.registration.model.Gender;
import com.gym.user.registration.model.Role;
import com.gym.user.registration.model.User;
import com.gym.user.registration.repository.UserRepository;
import com.gym.user.registration.service.CustomPasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class AdminInitializer implements InitializingBean {

    private final CustomPasswordEncoder customPasswordEncoder;
    private final UserRepository userRepository;
    private final String nickname;
    private final String password;
    private final String mail;

    public AdminInitializer(
            CustomPasswordEncoder customPasswordEncoder,
            UserRepository userRepository,
            @Value("${mock.gym.admin.mail}") String mail,
            @Value("${mock.gym.admin.nickname}") String nickname,
            @Value("${mock.gym.admin.password}") String password) {
        this.customPasswordEncoder = customPasswordEncoder;
        this.userRepository = userRepository;
        this.nickname = nickname;
        this.password = password;
        this.mail = mail;
    }

    @Override
    public void afterPropertiesSet() {
        Optional<User> user = userRepository.findByMail(mail);
        if (user.isEmpty()) {
            provisionAdmin();
        }
    }

    private void provisionAdmin() {
        log.info("Provisioning new admin at initialisation");
        User user = User.builder()
                .id(UUID.randomUUID())
                .mail(mail)
                .password(customPasswordEncoder.encode(password))
                .nickname(nickname)
                .gender(Gender.UNKNOWN)
                .isVerified(true)
                .isLocked(false)
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
    }
}

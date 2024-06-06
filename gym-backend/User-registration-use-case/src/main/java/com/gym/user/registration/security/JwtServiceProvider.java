package com.gym.user.registration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.gym.user.registration.repository.UserRepository;

@Component
public class JwtServiceProvider {

    private final UserRepository userRepository;

    public JwtServiceProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

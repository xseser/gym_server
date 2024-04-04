package com.gym.security;

import com.gym.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
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

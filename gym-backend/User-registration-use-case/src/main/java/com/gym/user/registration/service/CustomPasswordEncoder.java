package com.gym.user.registration.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encode(String stringToEncode) {
        return passwordEncoder.encode(stringToEncode);
    }
}

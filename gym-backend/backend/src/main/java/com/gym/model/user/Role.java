package com.gym.model.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {
    MEMBER, PREMIUM, SUPER_ADMIN, ADMIN;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return Arrays.stream(Role.values())
                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                .collect(Collectors.toList());
    }
}

package com.gym.user.registration.model.authorities;

import com.gym.user.registration.model.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.gym.user.registration.model.Role.ADMIN;
import static com.gym.user.registration.model.Role.MEMBER;
import static com.gym.user.registration.model.Role.PREMIUM;
import static com.gym.user.registration.model.Role.SUPER_ADMIN;

public abstract class Auth {

    public static List<SimpleGrantedAuthority> getAdminAuthorities() {
        return mapToSingleGrantedAuthority(List.of(MEMBER, PREMIUM, ADMIN));
    }

    public static List<SimpleGrantedAuthority> getPremiumAuthorities() {
        return mapToSingleGrantedAuthority(List.of(MEMBER, PREMIUM));
    }

    public static List<SimpleGrantedAuthority> getSuperAdminAuthorities() {
        return mapToSingleGrantedAuthority(List.of(MEMBER, PREMIUM, ADMIN, SUPER_ADMIN));
    }

    public static List<SimpleGrantedAuthority> getMemberAuthorities() {
        return mapToSingleGrantedAuthority(List.of(MEMBER));
    }

    private static List<SimpleGrantedAuthority> mapToSingleGrantedAuthority(List<Role> roles) {
        return roles.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                .collect(Collectors.toList());
    }
}

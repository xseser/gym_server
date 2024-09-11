package com.backend.gym.controller.proxy;

import com.gym.user.registration.model.Role;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;

public interface DataProvider {

    private String generateRandomString() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    default String getMail() {
        return generateRandomString() + "." + generateRandomString() + "@gmail.com";
    }

    default String getNickName() {
        return generateRandomString();
    }

    default Role getRole() {
        return Arrays.stream(Role.values())
                .findAny()
                .orElse(null);
    }

    default String getPassword() {
        return "Password123!" + generateRandomString();
    }

    default String getGender() {
        return "MAN";
    }
}

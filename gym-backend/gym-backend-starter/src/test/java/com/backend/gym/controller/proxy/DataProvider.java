package com.backend.gym.controller.proxy;

import org.apache.commons.lang3.RandomStringUtils;

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

    default String getPassword() {
        return "Password123!";
    }

    default String getGender() {
        return "MAN";
    }
}

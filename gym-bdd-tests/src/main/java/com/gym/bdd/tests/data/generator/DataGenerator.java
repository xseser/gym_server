package com.gym.bdd.tests.data.generator;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public abstract class DataGenerator {

    private static final Random random = new Random();

    private static String generateString(int size) {
        return RandomStringUtils.randomAlphanumeric(size);
    }

    public static String generateMail() {
        return generateString(20) + "." + generateString(30) + "@gmail.com";
    }

    public static String generatePassword() {
        return "Zz123!" + generateString(17);
    }

    public static String generateGender() {
        return "MAN";
    }

    public static String generateNickname() {
        return generateString(15);
    }
}

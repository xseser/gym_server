package com.gym.bdd.tests.property;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.util.Optional.ofNullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyReader {

    private static final Properties props;

    static {
        try (InputStream inputStream = new FileInputStream("src/test/resources/test.properties")) {
            props = new Properties();
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final static String ADMIN_HOLDER = "ADMIN";

    private static String getProperty(String key) {
        return ofNullable(System.getProperty(key))
                .orElse(props.getProperty(key));
    }

    public static String getAdminMail() {
        return getProperty("gym.admin.mail");
    }

    public static String getAdminPassword() {
        return getProperty("gym.admin.password");
    }

    public static String getAdminNickname() {
        return getProperty("gym.admin.nickname");
    }

    public static String getAdminMember() {
        return getProperty("gym.admin.member");
    }

    public static String getAdminGender() {
        return getProperty("gym.admin.gender");
    }
}

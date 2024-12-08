package com.gym.bdd.tests.http.url;

public abstract class UrlManagement {

    private static final String BACKEND_BASE_URL = "http://localhost:9876/MMT/api";
    private static final String MAIL_BASE_URL = "http://localhost:5443/MMT/api";
    public static final String REGISTRATION_URL = BACKEND_BASE_URL + "/v1/register";
    public static final String LOGIN_URL = BACKEND_BASE_URL + "/v1/login";
    public static final String PULL_CONFIRMATIONS_URL = MAIL_BASE_URL + "/internal/v1/mails";
    public static final String CONFIRMATION_URL = MAIL_BASE_URL + "/v1/confirm/{secret}";
}

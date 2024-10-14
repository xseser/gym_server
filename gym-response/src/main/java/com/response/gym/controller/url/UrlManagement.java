package com.response.gym.controller.url;

public abstract class UrlManagement {

    public static final String API_BASE = "MMT/api/v1/";
    public static final String API_INTERNAL = "MMT/api/internal/v1/";

    public static final String HEALTH = "health";

    public static final String USER_REGISTRATION = "register";
    public static final String USER_LOGIN = "login";
    public static final String CONFIRM_REGISTRATION = "confirm";
    public static final String REFRESH_TOKEN = "refresh";

    public static final String EMAIL_SEND = "trigger/email";
    public static final String EMAIL_SECRET = "emailSecret";
    public static final String EMAIL_TYPE = "emailType";
    public static final String EMAIL_CONFIRMATION_ENDPOINT = "confirm/";
    public static final String EMAIL_CONFIRMATION_PATH_VARIABLE = "{confirmation}";

    public static final String BACKEND_SECRET = "backendSecret";
    public static final String KAFKA_PRODUCER_ENDPOINT = "backendSecret";


}

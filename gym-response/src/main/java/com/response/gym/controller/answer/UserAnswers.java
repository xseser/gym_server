package com.response.gym.controller.answer;

public abstract class UserAnswers {

    public static final int INTERNAL_ERROR = -1;
    public static final int PASSWORDS_DOES_NOT_MATCH = 100000;
    public static final int INVALID_MAIL_CREDENTIALS = 100010;
    public static final int INVALID_NICKNAME_CREDENTIALS = 100020;
    public static final int INVALID_PASSWORD_CREDENTIALS = 100030;
    public static final int INVALID_GENDER_CREDENTIALS = 100040;
    public static final int GIVEN_USER_ALREADY_EXISTS = 100050;
    public static final int GIVEN_USER_WAS_NOT_FOUND = 100060;


    public static final int INVALID_LOGIN_CREDENTIALS = 100070;
    public static final int INVALID_VERIFICATION_STATE = 100080;
    public static final int INVALID_LOCK_STATE = 100090;
    public static final int UNKNOWN_ERROR_DURING_AUTHENTICATION = 100069;


    public static final int INVALID_VERIFICATION_FLAG = 100100;
    public static final int GIVEN_USER_VERIFICATION_STATE_IS_ALREADY_SET = 100110;
}


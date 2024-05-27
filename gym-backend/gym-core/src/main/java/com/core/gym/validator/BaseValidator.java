package com.core.gym.validator;

import cyclops.control.Either;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseValidator {

    public static Either<Integer, String> validate(String stringToValidation, int errorCode, String regex) {
        if(stringToValidation == null || stringToValidation.isEmpty()) {
            return Either.left(errorCode);
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToValidation);
        if(matcher.matches()) {
            return Either.right(stringToValidation.strip());
        }
        return Either.left(errorCode);
    }
}

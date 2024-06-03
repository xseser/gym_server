package com.backend.gym.controller.validator.impl.base;

import com.backend.gym.model.user.Gender;
import cyclops.control.Either;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.response.gym.controller.answer.UserAnswers.INVALID_GENDER_CREDENTIALS;

public abstract class GenderValidator {

    private static Either<Integer, Gender> validate(String gender) {
        if (gender == null || gender.isEmpty()) {
            return Either.right(Gender.UNKNOWN);
        }
        String regex = "^(?:MAN|WOMAN)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(gender.strip().toUpperCase());
        if (matcher.matches()) {
            return Either.right(Gender.valueOf(matcher.group(0)));
        }
        return Either.left(INVALID_GENDER_CREDENTIALS);
    }

    public static Either<Integer, Gender> genderValidator(String gender, Consumer<Gender> applyFunction) {
        return validate(gender)
                .peek(applyFunction);
    }
}

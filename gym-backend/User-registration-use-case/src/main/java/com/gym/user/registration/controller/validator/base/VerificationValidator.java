package com.gym.user.registration.controller.validator.base;

import cyclops.control.Either;

import java.util.function.Consumer;

import static com.response.gym.controller.answer.UserAnswers.INVALID_VERIFICATION_FLAG;

public abstract class VerificationValidator {

    private static Either<Integer, Boolean> validate(Boolean flag) {
        if (flag == null) {
            return Either.left(INVALID_VERIFICATION_FLAG);
        }
        return Either.right(flag);
    }

    public static Either<Integer, Boolean> verificationFlagValidator(Boolean flag, Consumer<Boolean> applyFunction) {
        return validate(flag).peek(applyFunction);
    }
}

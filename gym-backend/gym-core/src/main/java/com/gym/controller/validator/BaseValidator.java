package com.gym.controller.validator;

import com.gym.common.response.BadRequest;
import com.gym.common.response.MMTResponseCreator;
import com.hubspot.algebra.Result;

sealed abstract class BaseValidator permits Validator {

    protected static Result<String, MMTResponseCreator> simpleStringValidator(String check) {
        if (check == null || check.isEmpty()) {
            return Result.err(new BadRequest());
        }
        return Result.ok(check.strip());
    }
}

package com.core.gym.validator;

import com.response.gym.response.BadRequest;
import com.response.gym.response.MMTResponseCreator;
import cyclops.control.Either;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidatorHelperV2 {

    @SafeVarargs
    private Optional<Integer> forEachValidation(Either<Integer, ?>... results) {
        for (Either<Integer, ?> validationResult : results) {
            if (validationResult.isLeft()) {
                return validationResult
                        .getLeft()
                        .toOptional();
            }
        }
        return Optional.empty();
    }

    @SafeVarargs
    public final Optional<MMTResponseCreator> validate(Either<Integer, ?>... results) {
        return forEachValidation(results)
                .map(BadRequest::new);
    }
}

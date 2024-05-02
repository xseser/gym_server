package com.gym.controller.validator;

import com.gym.common.response.MMTResponseCreator;
import com.hubspot.algebra.Result;
import org.apache.commons.lang3.tuple.Triple;

import java.util.function.Consumer;
import java.util.function.Function;

public class ValidatorHelper<BASE_VALIDATION> {

    @SafeVarargs
    public final void assignDataToValidRequest(Function<BASE_VALIDATION, Result<String, MMTResponseCreator>> action,
            Triple<Consumer<String>, BASE_VALIDATION, Integer>... consumer) {
        for (Triple<Consumer<String>, BASE_VALIDATION, Integer> pairs : consumer) {
            String validatedString = action.apply(pairs.getMiddle()).expect(String.valueOf(pairs.getRight()));
            pairs.getLeft().accept(validatedString);
        }
    }
}

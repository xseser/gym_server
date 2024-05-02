package com.gym.controller.processor;

import com.gym.common.response.MMTResponseCreator;
import com.gym.controller.validator.Validator;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ControllerProcessor<REQUEST, VALID> {

    private final Validator<REQUEST, VALID> validator;

    public ControllerProcessor(Validator<REQUEST, VALID> validator) {
        this.validator = validator;
    }

    public MMTResponseCreator process(REQUEST request, Function<VALID, MMTResponseCreator> applier) {
        return validator.validateAndInsertBehaviour(request, applier);
    }
}




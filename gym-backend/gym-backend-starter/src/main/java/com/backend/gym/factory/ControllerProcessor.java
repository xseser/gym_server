package com.backend.gym.factory;

import com.core.gym.factory.ValidatorFactory;
import com.core.gym.validator.Validator;
import com.response.gym.response.MMTResponseCreator;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ControllerProcessor<REQUEST, VALID> {

    private final ValidatorFactory validatorFactory;

    public ControllerProcessor(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }

    public MMTResponseCreator process(
            REQUEST request,
            Class<? extends Validator<REQUEST, VALID>> validatorClass,
            Function<VALID, MMTResponseCreator> applier) {

        return validatorFactory.getValidator(validatorClass)
                .validateAndInsertBehaviour(request, applier);
    }
}

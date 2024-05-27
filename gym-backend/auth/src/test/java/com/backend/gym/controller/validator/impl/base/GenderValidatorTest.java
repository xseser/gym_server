package com.backend.gym.controller.validator.impl.base;

import com.backend.gym.controller.validator.impl.BaseIntegrationTest;
import com.backend.gym.controller.validator.impl.BaseUserValidator;
import com.backend.gym.controller.request.dto.base.UserRegistrationDto;
import org.junit.Test;

public class GenderValidatorTest extends BaseIntegrationTest implements BaseUserValidator {

    @Test
    public void nullableGenderCausesUnknownGenderAssignment() {
        //given
        UserRegistrationDto userRegistrationDto = provideUserRegistrationData();
        userRegistrationDto.setGender(null);

        //when & then
        validateValidUserRegistrationRequest(userRegistrationDto);
    }

}
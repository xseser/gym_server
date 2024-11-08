package com.gym.mail.generator.service.time;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Profile("mock")
@Component
public class MockedExpirationTime implements ExpirationDateTimeProvider {

    @Override
    public LocalDateTime getExpirationDateTime() {
        return LocalDateTime.now().plusSeconds(10);
    }
}

package com.gym.bdd.tests.step.dto.request.confirmation;

import com.gym.bdd.tests.step.dto.request.RequestMarker;

public record Secret(String secret) implements RequestMarker {
}

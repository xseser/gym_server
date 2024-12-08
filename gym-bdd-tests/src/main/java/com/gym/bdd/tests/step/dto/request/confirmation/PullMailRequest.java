package com.gym.bdd.tests.step.dto.request.confirmation;

import com.gym.bdd.tests.step.dto.request.RequestMarker;

public record PullMailRequest(String nickname) implements RequestMarker {
}

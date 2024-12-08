package com.gym.bdd.tests.utils;

import com.gym.bdd.tests.utils.model.AtMost;
import com.gym.bdd.tests.utils.model.PollInterval;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.awaitility.Awaitility;

import java.util.concurrent.Callable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Awaiter {

    public static void awaitFor(AtMost atMost, PollInterval pollInterval, Callable<Boolean> predicate) {
        Awaitility.await()
                .atMost(atMost.time(), atMost.unit())
                .pollInterval(pollInterval.time(), pollInterval.unit())
                .until(predicate);
    }
}

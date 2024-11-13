package com.gym.bdd.tests.init.serenity.cucumber;

import com.gym.bdd.tests.init.serenity.SystemHolder;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public abstract class Holder {

    private static final String GYM_HOLDER = "GYM_HOLDER";

    public static void initialiseSystemHolder(SystemHolder holder) {
        setSessionVariable(GYM_HOLDER).to(holder);
    }

    public static SystemHolder getSystemHolder() {
        return sessionVariableCalled(GYM_HOLDER);
    }
}

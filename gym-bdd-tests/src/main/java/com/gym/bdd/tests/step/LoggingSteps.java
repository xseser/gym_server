package com.gym.bdd.tests.step;

import com.gym.bdd.tests.serenity.SystemHolder;
import com.gym.bdd.tests.step.dto.request.UserLoginInRequest;
import com.gym.bdd.tests.step.dto.response.UserLoginResponse;
import com.gym.bdd.tests.step.dto.response.UserRegistrationResponse;
import io.restassured.http.ContentType;

import static com.gym.bdd.tests.serenity.cucumber.Holder.getSystemHolder;
import static io.restassured.RestAssured.given;

public class LoggingSteps {

    public void givenUserLoggsIn(String userOrder) {
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(new UserLoginInRequest("dkalsddm2kadsa111k", "Poznan123!"))
                .log().all()
                .when()
                .post("http://localhost:9876/MMT/api/v1/login")
                .prettyPeek();

        SystemHolder systemHolder = getSystemHolder();
        System.out.println("kurwa system holder " + systemHolder);
    }
 }

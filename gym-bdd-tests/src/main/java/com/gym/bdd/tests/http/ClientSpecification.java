package com.gym.bdd.tests.http;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.config.ConnectionConfig.connectionConfig;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientSpecification {

    private static final int HTTP_MOBILE_CONNECTION_TIMEOUT = 30000;
    private static final int HTTP_MOBILE_SOCKET_TIMEOUT = 30000;
    private static final int HTTP_MOBILE_CONNECTION_MANAGER_TIMEOUT = 30000;

    private static final HttpClientConfig HTTP_MOBILE_CLIENT_CONFIG = HttpClientConfig.httpClientConfig()
            .setParam("http.connection.timeout", HTTP_MOBILE_CONNECTION_TIMEOUT)
            .setParam("http.socket.timeout", HTTP_MOBILE_SOCKET_TIMEOUT)
            .setParam("http.connection-manager.timeout", HTTP_MOBILE_CONNECTION_MANAGER_TIMEOUT);

    public static RequestSpecification getSpecification() {
        return given()
                .filter(new AllureRestAssured())
                .log().all()
                .config(RestAssuredConfig.newConfig()
                        .logConfig(LogConfig.logConfig()
                                .enableLoggingOfRequestAndResponseIfValidationFails())
                        .connectionConfig(connectionConfig().closeIdleConnectionsAfterEachResponseAfter(10, TimeUnit.SECONDS))
                        .httpClient(HTTP_MOBILE_CLIENT_CONFIG));
    }
}

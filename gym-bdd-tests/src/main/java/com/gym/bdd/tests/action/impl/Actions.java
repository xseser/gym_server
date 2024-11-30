package com.gym.bdd.tests.action.impl;

import com.gym.bdd.tests.step.dto.comparator.ComparatorMarker;
import com.gym.bdd.tests.step.dto.error.ErrorDto;
import com.gym.bdd.tests.step.dto.request.RequestMarker;
import com.gym.bdd.tests.step.dto.response.ResponseMarker;
import cyclops.control.Either;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public abstract class Actions<T extends RequestMarker, F extends ResponseMarker, E extends ComparatorMarker> {

    protected void makeAction(T request, ErrorDto code) {
        performServerAction(request)
                .bipeek(
                        response -> validate(response, request),
                        errorCode -> validate(code, errorCode));
    }

    private Either<F, ErrorDto> performServerAction(T request) {
        Response response = serverRequest(request);
        if (response.getStatusCode() == getValidResponseCode()) {
            return Either.left(getBodyFromResponse(response));
        }
        return Either.right(new ErrorDto(Integer.valueOf(response.getBody().asString())));
    }

    private void validate(F response, T request) {
        Assertions
                .assertThat(mapToComparator(response))
                .isEqualTo(mapToComparator(request));
    }

    private void validate(ErrorDto expectedErrorCode, ErrorDto givenErrorCode) {
        Assertions.assertThat(givenErrorCode).isEqualTo(expectedErrorCode);
    }

    public abstract Response serverRequest(T request);

    public abstract E mapToComparator(F response);

    public abstract E mapToComparator(T request);

    public abstract int getValidResponseCode();

    public abstract F getBodyFromResponse(Response response);
}

package com.backend.gym.common.response;

import com.response.gym.response.BadRequest;
import com.response.gym.response.Conflict;
import com.response.gym.response.Created;
import com.response.gym.response.MMTResponseCreator;
import com.response.gym.response.NotFound;
import com.response.gym.response.Ok;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CONFLICT;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

class MMTResponseCreatorTest {

    @Test
    public void flashCardResponseCreator__canInclude__OK() {
        //Given
        MMTResponseCreator flashCardResponseCreator = new Ok();

        //when
        ResponseEntity response = flashCardResponseCreator.makeResponse();

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(HTTP_OK));
    }

    @Test
    public void flashCardResponseCreator__canInclude__CREATED() {
        //Given
        MMTResponseCreator flashCardResponseCreator = new Created();

        //when
        ResponseEntity response = flashCardResponseCreator.makeResponse();

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(HTTP_CREATED));
    }

    @Test
    public void flashCardResponseCreator__canInclude__CONFLICT() {
        //Given
        MMTResponseCreator flashCardResponseCreator = new Conflict();

        //when
        ResponseEntity response = flashCardResponseCreator.makeResponse();

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(HTTP_CONFLICT));
    }

    @Test
    public void flashCardResponseCreator__canInclude__BADREQUEST() {
        //Given
        MMTResponseCreator flashCardResponseCreator = new BadRequest();

        //when
        ResponseEntity response = flashCardResponseCreator.makeResponse();

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(HTTP_BAD_REQUEST));
    }

    @Test
    public void flashCardResponseCreator__canInclude__NOTFOUND() {
        //Given
        MMTResponseCreator flashCardResponseCreator = new NotFound();

        //when
        ResponseEntity response = flashCardResponseCreator.makeResponse();

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(HTTP_NOT_FOUND));
    }
}
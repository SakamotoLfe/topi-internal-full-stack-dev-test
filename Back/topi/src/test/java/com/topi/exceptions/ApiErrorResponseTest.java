package com.topi.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorResponseTest {

    ApiErrorResponse apiErrorResponse;

    @BeforeEach
    void setUp() {
        apiErrorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, 500,
                "Error! Heap memory's not enough!");
    }

    @Test
    void getStatus() {
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, apiErrorResponse.getStatus());
    }

    @Test
    void setStatus() {
        Assertions.assertDoesNotThrow(() -> apiErrorResponse.setStatus(HttpStatus.BAD_GATEWAY));
    }

    @Test
    void getCode() {
        Assertions.assertEquals(500, apiErrorResponse.getCode());
    }

    @Test
    void setCode() {
        Assertions.assertDoesNotThrow(() -> apiErrorResponse.setCode(502));
    }

    @Test
    void getMessage() {
        Assertions.assertEquals("Error! Heap memory's not enough!", apiErrorResponse.getMessage());
    }

    @Test
    void setMessage() {
        Assertions.assertDoesNotThrow(() -> apiErrorResponse.setMessage("Ops, looks like the Gateway just closed"));
    }
}
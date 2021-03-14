package com.topi.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Class created to be a basic error response from the back-end to front-end.
 *
 * @since 2021-02-24
 */

public class ApiErrorResponse extends RuntimeException {

    /**
     * HTTP Code.
     */
    private HttpStatus status;

    /**
     * Internal Error Code.
     */
    private int code;

    /**
     * Custom Message to be returned.
     */
    private String message;

    /**
     * Constructor with params.
     *
     * @param status  HTTP Code.
     * @param code    Internal Error Code.
     * @param message Custom Message to be returned.
     */
    public ApiErrorResponse(HttpStatus status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    // Getters e Setters

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
package com.topi.exceptions;

/**
 * Class created to be the basic NOT FOUND exception of the system.
 *
 * @since 2021-02-24
 */

public class NotFoundException extends RuntimeException {

    /**
     * Empty constructor.
     */
    public NotFoundException() {
        super();
    }

    /**
     * Constructor with params.
     *
     * @param message Custom message.
     */
    public NotFoundException(String message) {
        super(message);
    }
}

package com.topi.exceptions;

/**
 * Enum that contains the basics mapped errors of the system.
 *
 * @since 2021-02-24
 */

public enum CodeError {

    /**
     * An error that's not mapped.
     */
    UNKNOWN("CM-500");

    /**
     * Error code.
     */
    private final String code;

    /**
     * Constructor with params.
     *
     * @param code Error code.
     */
    CodeError(String code) {
        this.code = code;
    }

    /**
     * Error description.
     *
     * @return {@link Integer}. Código do erro.
     */
    public String getDescription() {
        return code;
    }
}


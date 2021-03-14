package com.topi.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeErrorTest {

    CodeError codeError;

    @BeforeEach
    void setUp() {
        codeError = CodeError.UNKNOWN;
    }

    @Test
    void getDescription() {
        Assertions.assertEquals("CM-500", codeError.getDescription());
    }

    @Test
    void values() {
        Assertions.assertEquals(1, CodeError.values().length);
    }

    @Test
    void valueOf() {
        Assertions.assertEquals(codeError, CodeError.valueOf("UNKNOWN"));
    }
}
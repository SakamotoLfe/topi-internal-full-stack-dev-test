package com.topi.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {

    NotFoundException notFoundException;

    @Test
    void NotFoundExceptionNotNullMessage(){
        notFoundException = new NotFoundException("Error! something is wrong...");
        Assertions.assertNotNull(notFoundException);
        Assertions.assertEquals("Error! something is wrong...", notFoundException.getMessage());
    }

    @Test
    void NotFoundExceptionNotNull(){
        notFoundException = new NotFoundException();
        Assertions.assertNotNull(notFoundException);
    }
}
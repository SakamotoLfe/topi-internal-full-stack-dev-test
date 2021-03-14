package com.topi.predicate.criteria;

import com.querydsl.core.types.dsl.PathBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class StringExpressionTest {

    StringExpression stringExpressionTest;

    @MockBean
    PathBuilder pathBuilder;

    @BeforeEach
    void setUp() {
        pathBuilder = new PathBuilder(Boolean.class, "deletado");
        stringExpressionTest = new StringExpression();
    }

    @Test
    @DisplayName("Get Expression.")
    void getExpression() {

        assertNotNull(stringExpressionTest
                .getExpression(pathBuilder, Integer.class, "deletado", "!", ""));
        assertDoesNotThrow(() -> stringExpressionTest
                .getExpression(pathBuilder, Integer.class, "deletado", "!", ""));

        assertNotNull(stringExpressionTest
                .getExpression(pathBuilder, Integer.class, "deletado", "::", ""));
        assertDoesNotThrow(() -> stringExpressionTest
                .getExpression(pathBuilder, Integer.class, "deletado", "::", ""));

        assertNotNull(stringExpressionTest
                .getExpression(pathBuilder, Integer.class, "deletado", ":", ""));
        assertDoesNotThrow(() -> stringExpressionTest
                .getExpression(pathBuilder, Integer.class, "deletado", ":", ""));

        assertNotNull(stringExpressionTest
                .getExpression(pathBuilder, Integer.class, "deletado", "?", ""));
        assertDoesNotThrow(() -> stringExpressionTest
                .getExpression(pathBuilder, Integer.class, "deletado", "?", ""));
    }
}

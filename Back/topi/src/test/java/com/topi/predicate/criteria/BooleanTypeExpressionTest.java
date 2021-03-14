package com.topi.predicate.criteria;

import com.querydsl.core.types.dsl.PathBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BooleanTypeExpressionTest {

    BooleanTypeExpression booleanTypeExpression;

    @MockBean
    PathBuilder pathBuilder;

    @BeforeEach
    void srtarUp() {
        pathBuilder = new PathBuilder(Boolean.class, "deletado");
    }

    @Test
    @DisplayName("Get Expression do tipo Booleano.")
    void getExpression() {
        booleanTypeExpression = new BooleanTypeExpression();
        assertNotNull(booleanTypeExpression
                .getExpression(pathBuilder, Boolean.class, "ORDINAL", ":", "true"));
        assertDoesNotThrow(() -> booleanTypeExpression
                .getExpression(pathBuilder, Boolean.class, "ORDINAL", ":", "true"));

        assertNotNull(booleanTypeExpression
                .getExpression(pathBuilder, Boolean.class, "ORDINAL", "!", "true"));
        assertDoesNotThrow(() -> booleanTypeExpression
                .getExpression(pathBuilder, Boolean.class, "ORDINAL", "!", "true"));

        assertNotNull(booleanTypeExpression
                .getExpression(pathBuilder, Boolean.class, "ORDINAL", "::", "true"));
        assertDoesNotThrow(() -> booleanTypeExpression
                .getExpression(pathBuilder, Boolean.class, "ORDINAL", "::", "true"));

        assertNull(booleanTypeExpression
                .getExpression(pathBuilder, Boolean.class, "ORDINAL", "", "true"));
        assertDoesNotThrow(() -> booleanTypeExpression
                .getExpression(pathBuilder, Boolean.class, "ORDINAL", "", "true"));
    }
}

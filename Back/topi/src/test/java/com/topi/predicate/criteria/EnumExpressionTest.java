package com.topi.predicate.criteria;

import com.querydsl.core.types.dsl.PathBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.EnumSelector;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class EnumExpressionTest {

    EnumExpression enumExpression;

    @MockBean
    PathBuilder pathBuilder;

    @BeforeEach
    void srtarUp() {
        pathBuilder = new PathBuilder(Boolean.class, "deletado");
    }

    @Test
    @DisplayName("Get Expression de Enuns.")
    void getExpression() {
        enumExpression = new EnumExpression();
        assertNotNull(enumExpression
                .getExpression(pathBuilder, EnumSelector.class, "deletado", ":", "ORDINAL"));
        assertDoesNotThrow(() -> enumExpression
                .getExpression(pathBuilder, EnumSelector.class, "deletado", ":", "ORDINAL"));

        assertNotNull(enumExpression
                .getExpression(pathBuilder, EnumSelector.class, "deletado", "!", "ORDINAL"));
        assertDoesNotThrow(() -> enumExpression
                .getExpression(pathBuilder, EnumSelector.class, "deletado", "!", "ORDINAL"));

        assertNotNull(enumExpression
                .getExpression(pathBuilder, EnumSelector.class, "deletado", "::", "ORDINAL"));
        assertDoesNotThrow(() -> enumExpression
                .getExpression(pathBuilder, EnumSelector.class, "deletado", "::", "ORDINAL"));

        assertNull(enumExpression
                .getExpression(pathBuilder, EnumSelector.class, "deletado", "", "ORDINAL"));
        assertDoesNotThrow(() -> enumExpression
                .getExpression(pathBuilder, EnumSelector.class, "deletado", "", "ORDINAL"));
    }

}

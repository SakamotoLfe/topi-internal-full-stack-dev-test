package com.topi.predicate.criteria;

import com.querydsl.core.types.dsl.PathBuilder;
import com.topi.predicate.criteria.NumberExpression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.EnumSelector;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NumberExpressionTest {

    NumberExpression numberExpression;

    @MockBean
    PathBuilder pathBuilder;

    @BeforeEach
    void setUp() {
        pathBuilder = new PathBuilder(Boolean.class, "deletado");
        numberExpression = new NumberExpression();
    }

    @Test
    @DisplayName("Get Expression.")
    void getExpressionIgual() {
        assertNotNull(numberExpression
                .getExpression(pathBuilder, EnumSelector.class, "deletado", ":", "22.8"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, EnumSelector.class, "deletado", ":", "22.8"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, EnumSelector.class, "deletado", ":", "22"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, EnumSelector.class, "deletado", ":", "22"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, EnumSelector.class, "deletado", ":", "22.8"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, EnumSelector.class, "deletado", ":", "22.8"));

    }

    @Test
    @DisplayName("Get Expression.")
    void getExpressionMaiorOuIgual() {
        assertNotNull(numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", ">:", "22"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", ">:", "22"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", ">:", "22.8"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", ">:", "22.8"));
    }

    @Test
    @DisplayName("Get Expression.")
    void getExpressionMenorOuIgual() {
        assertNotNull(numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "<:", "22"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "<:", "22"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "<:", "22.08"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "<:", "22.08"));
    }

    @Test
    @DisplayName("Get Expression.")
    void getExpressionMaior() {
        assertNotNull(numberExpression
                .getExpression(pathBuilder, Double.class, "deletado", ">", "22.08"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Double.class, "deletado", ">", "22.08"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", ">", "22"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", ">", "22"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", ">", "22"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", ">", "22"));
    }

    @Test
    @DisplayName("Get Number Expression.")
    void getExpressionMenor() {
        assertNotNull(numberExpression
                .getExpression(pathBuilder, Double.class, "deletado", "<", "22.8"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Double.class, "deletado", "<", "22.8"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "<", "22"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "<", "22"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, Double.class, "deletado", "<", "22.8"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Double.class, "deletado", "<", "22.8"));
    }


    @Test
    @DisplayName("Get Number Expression.")
    void getExpressionDiferente() {
        assertNotNull(numberExpression
                .getExpression(pathBuilder, Double.class, "deletado", "!", "22.0805"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Double.class, "deletado", "!", "22.0805"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "::", "22"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "::", "22"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "::", "22.8"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "::", "22.8"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "::", "22"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "::", "22"));
    }

    @Test
    @DisplayName("Get Number Expression.")
    void getExpressionEhNulo() {
        assertNotNull(numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "<:", "22.08"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "<:", "22.08"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, Double.class, "deletado", "!", "22.0805"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Double.class, "deletado", "!", "22.0805"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, Double.class, "deletado", "!", "22.08"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Double.class, "deletado", "!", "22.08"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "!", "22"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "!", "22"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "::", "22"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Integer.class, "deletado", "::", "22"));

        assertNotNull(numberExpression
                .getExpression(pathBuilder, Double.class, "deletado", "::", "22.08"));
        assertDoesNotThrow(() -> numberExpression
                .getExpression(pathBuilder, Double.class, "deletado", "::", "22.08"));
    }
}

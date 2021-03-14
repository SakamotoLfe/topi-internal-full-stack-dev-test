package com.topi.predicate.criteria;

import com.querydsl.core.types.dsl.PathBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateExpressionTest {

    DateExpression dateExpression;

    @MockBean
    PathBuilder pathBuilder;

    @BeforeEach
    void srtarUp() {
        dateExpression = new DateExpression();
        pathBuilder = new PathBuilder(Boolean.class, "deletado");
    }

    @Test
    @DisplayName("Get Expression de Data.")
    void getExpression() {
        assertNotNull(dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", ":", "22-01-2021"));
        assertDoesNotThrow(() -> dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", ":", "22-01-2021"));

        assertNotNull(dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", "!", "22-01-2021"));
        assertDoesNotThrow(() -> dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", "!", "22-01-2021"));

        assertNotNull(dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", "::", "22-01-2021"));
        assertDoesNotThrow(() -> dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", "::", "22-01-2021"));

        assertNull(dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", "", "22-01-2021"));
        assertDoesNotThrow(() -> dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", "", "22-01-2021"));

        assertNotNull(dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", ">", "22-01-2021"));
        assertDoesNotThrow(() -> dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", ">", "22-01-2021"));

        assertNotNull(dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", "<", "22-01-2021"));
        assertDoesNotThrow(() -> dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", "<", "22-01-2021"));

        assertThrows(IllegalArgumentException.class, () -> dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", ":", "01_2021"));

        assertNotNull(dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", ":", "01-2021"));
        assertDoesNotThrow(() -> dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", ":", "01-2021"));

        assertNotNull(dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", ":", "02022020_02022021"));
        assertDoesNotThrow(() -> dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", ":", "02022020_02022021"));

        assertNotNull(dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", ">", "01-2021"));
        assertDoesNotThrow(() -> dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", ">", "01-2021"));

        assertNotNull(dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", "<", "01-2021"));
        assertDoesNotThrow(() -> dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", "<", "01-2021"));

    }

    @Test
    @DisplayName("Get Expression de Data exceptions.")
    void getExpressionErros() {
        assertThrows(IllegalArgumentException.class, () -> dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", "<", "01_2021"));

        assertThrows(IllegalArgumentException.class, () -> dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", ">", "01_2021"));

        assertThrows(IllegalArgumentException.class, () -> dateExpression
                .getExpression(pathBuilder, Boolean.class, "deletado", ":", "asjdaçsdf"));
    }
}

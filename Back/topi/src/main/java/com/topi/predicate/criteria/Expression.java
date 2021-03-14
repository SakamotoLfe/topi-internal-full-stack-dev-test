package com.topi.predicate.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;

/**
 * Basic Interface created to make logic expressions.
 *
 * @since 2021-02-23
 */

public interface Expression {

    /**
     * Method that is responsible for the predicate execution.
     *
     * @param pathBuilder Expression with key and value.
     * @param type        Expression type.
     * @param key         Expression key.
     * @param operation   Operation AND, OR, EQUALS, NOT IN...
     * @param val         Expression value.
     * @return {@link BooleanExpression}. Result of the expression
     */
    BooleanExpression getExpression(PathBuilder<?> pathBuilder, Class type, String key, String operation, String val);

}

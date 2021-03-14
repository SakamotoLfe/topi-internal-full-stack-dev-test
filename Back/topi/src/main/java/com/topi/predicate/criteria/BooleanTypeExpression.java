package com.topi.predicate.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.PathBuilder;

/**
 * Class created to do logic operations to predicate searches.
 *
 * @since 2021-02-23
 */

public class BooleanTypeExpression implements Expression {

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
    @Override
    public BooleanExpression getExpression(PathBuilder pathBuilder, Class type, String key, String operation, String val) {
        Boolean value = Boolean.valueOf(val);

        BooleanPath path = pathBuilder.getBoolean(key);
        if (operation.equalsIgnoreCase(":")) {
            return path.eq(value);
        }

        if (operation.equalsIgnoreCase("!")) {
            return path.notIn(value);
        }

        if (operation.equalsIgnoreCase("::")) {
            return path.isNull();
        }
        return null;
    }
}
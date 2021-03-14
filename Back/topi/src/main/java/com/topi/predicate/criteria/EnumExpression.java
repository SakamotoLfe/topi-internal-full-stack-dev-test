package com.topi.predicate.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.PathBuilder;

/**
 * Class created to make logic expressions with Enums.
 *
 * @since 2021-02-23
 */

public class EnumExpression implements Expression {

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
    public BooleanExpression getExpression(PathBuilder pathBuilder, Class type, String key,
                                           String operation, String val) {
        Enum enumObject = Enum.valueOf(type, val.toUpperCase());
        EnumPath path = pathBuilder.getEnum(key, type);

        if (operation.equalsIgnoreCase("!")) {
            return path.notIn(val);
        }

        if (operation.equalsIgnoreCase("::")) {
            return path.isNull();
        }

        if (operation.equalsIgnoreCase(":")) {
            return path.eq(enumObject);
        }
        return null;
    }
}

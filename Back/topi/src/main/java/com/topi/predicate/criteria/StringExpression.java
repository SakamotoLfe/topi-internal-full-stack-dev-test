package com.topi.predicate.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

/**
 * Class created to make logic expressions with Strings.
 *
 * @since 2021-02-23
 */

public class StringExpression implements Expression {

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
        StringPath path = pathBuilder.getString(key);

        if (operation.equalsIgnoreCase("!")) {
            return path.notEqualsIgnoreCase(val);
        }

        if (operation.equalsIgnoreCase("::")) {
            return path.isNull();
        }
        if (operation.equalsIgnoreCase(":")) {
            return path.containsIgnoreCase(val);
        }
        if (operation.equalsIgnoreCase("?")) {
            String v = "%".concat(val).concat("%");
            return path.likeIgnoreCase(v);
        }

        return null;
    }

}

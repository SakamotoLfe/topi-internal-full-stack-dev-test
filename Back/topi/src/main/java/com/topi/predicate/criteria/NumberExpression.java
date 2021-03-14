package com.topi.predicate.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Class created to make logic expressions with Numbers.
 *
 * @since 2021-02-23
 */

public class NumberExpression implements Expression {

    /**
     * Possible operations with numbers.
     */
    static Map<String, NumberOperation> operacoes = new HashMap<>();

    {
        operacoes.put(":", new NumberOperationEquals());
        operacoes.put(">:", new NumberOperationHigherOrEquals());
        operacoes.put("<:", new NumberOperationLowerOrEquals());
        operacoes.put(">", new NumberOperationHigher());
        operacoes.put("<", new NumberOperationLower());
        operacoes.put("::", new NumberOperationISNull());
        operacoes.put("!", new NumberOperationDifferent());
    }

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
        NumberPath path = pathBuilder.getNumber(key, type);
        NumberOperation numberOperation = operacoes.get(operation);
        return numberOperation.getNumberExpression(path, val);
    }

    /**
     * Number operation.
     */
    interface NumberOperation {
        BooleanExpression getNumberExpression(NumberPath path, String valor);
    }

    /**
     * Null Number operation.
     */
    static class NumberOperationISNull implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            return path.isNull();
        }
    }

    /**
     * Higher or Equals operation.
     */
    class NumberOperationHigherOrEquals implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            if (valorIsDecimal(val)) {
                double value = Double.parseDouble(val);
                return path.goe(value);
            } else {
                int value = Integer.parseInt(val);
                return path.goe(value);
            }

        }
    }

    /**
     * Lower or Equals operation.
     */
    class NumberOperationLowerOrEquals implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            if (valorIsDecimal(val)) {
                double value = Double.parseDouble(val);
                return path.loe(value);
            } else {
                int value = Integer.parseInt(val);
                return path.loe(value);
            }
        }
    }

    /**
     * Equals operation.
     */
    class NumberOperationEquals implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            if (val.equals("null")) {
                return path.isNull();
            }

            if (valorIsDecimal(val)) {
                double value = Double.parseDouble(val);
                return path.eq(value);
            } else {
                Integer value = Integer.parseInt(val);
                return path.eq(value);
            }

        }
    }

    /**
     * Higher operation.
     */
    class NumberOperationHigher implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            if (valorIsDecimal(val)) {
                double value = Double.parseDouble(val);
                return path.gt(value);
            } else {
                int value = Integer.parseInt(val);
                return path.gt(value);
            }
        }
    }

    /**
     * lower operation.
     */
    class NumberOperationLower implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            if (valorIsDecimal(val)) {
                double value = Double.parseDouble(val);
                return path.lt(value);
            } else {
                int value = Integer.parseInt(val);
                return path.lt(value);
            }
        }
    }

    /**
     * Different operation.
     */
    class NumberOperationDifferent implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            if (valorIsDecimal(val)) {
                double value = Double.parseDouble(val);
                return path.notIn(value);
            } else {
                int value = Integer.parseInt(val);
                return path.notIn(value);
            }
        }
    }

    /**
     * If the value floats or don't.
     *
     * @param val Value received.
     * @return {@link Boolean}. If the values floats.
     */
    private boolean valorIsDecimal(String val) {
        boolean contains = val.contains(".") || val.contains(",");
        return contains;
    }


}
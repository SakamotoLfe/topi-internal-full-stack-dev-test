package com.topi.predicate.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.PathBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Class created to make logic expressions with Dates.
 *
 * @since 2021-02-23
 */

public class DateExpression implements Expression {

    /**
     * Method that see if the date is composed or if it's simple.
     *
     * @param valor Date received.
     * @return {@link Boolean}. Result of the operation.
     */
    private Boolean isValorComposto(String valor) {
        return Arrays.asList(valor.split("_")).size() == 2;
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
        DatePath path = pathBuilder.getDate(key, Date.class);
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        if (operation.equalsIgnoreCase("!")) {
            return path.notIn(val);
        }
        if (operation.equalsIgnoreCase("::")) {
            return path.isNull();
        }
        if (operation.equalsIgnoreCase(":")) {
            if (isValorComposto(val)) {
                String[] datas = val.split("_");
                try {
                    Date dataInicial = sdf.parse(datas[0]);
                    Date dataFinal = sdf.parse(datas[1]);
                    dataFinal.setHours(23);
                    dataFinal.setMinutes(59);
                    dataFinal.setSeconds(59);

                    return path.between(dataInicial, dataFinal);
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            } else {
                try {
                    Date data = sdf.parse(val);
                    return path.eq(data);

                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        } else if (operation.equals("<")) {
            try {
                Date data = sdf.parse(val);
                data.setHours(23);
                data.setMinutes(59);
                data.setSeconds(59);

                return path.loe(data);

            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        } else if (operation.equals(">")) {
            try {
                Date data = sdf.parse(val);
                data.setHours(0);
                data.setMinutes(0);
                data.setSeconds(0);

                return path.goe(data);

            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return null;
    }
}

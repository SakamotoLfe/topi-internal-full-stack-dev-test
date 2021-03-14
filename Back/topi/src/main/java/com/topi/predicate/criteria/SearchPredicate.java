package com.topi.predicate.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class created to be a basic implementation of a Search Predicate.
 *
 * @since 10/12/2020
 */

public class SearchPredicate<T> {

    /**
     * First level of the search.
     */
    public static final Integer FIRST = 1;

    /**
     * Second level of the search.
     */
    public static final Integer SECOND = 2;

    /**
     * Third level of the search.
     */
    public static final Integer THIRD = 3;

    /**
     * Fourth level of the search.
     */
    public static final Integer FOURTH = 4;

    /**
     * Class that specifies the predicate.
     */
    private final Class<T> specification;

    /**
     * Search that contains the criteira that is being searched.
     */
    private final String search;

    /**
     * Possible logical operations.
     */
    private static final Map<Class, Expression> expressions = new HashMap<>();

    {
        expressions.put(Integer.class, new NumberExpression());
        expressions.put(Long.class, new NumberExpression());
        expressions.put(Enum.class, new EnumExpression());
        expressions.put(String.class, new StringExpression());
        expressions.put(Date.class, new DateExpression());
        expressions.put(Double.class, new NumberExpression());
        expressions.put(Float.class, new NumberExpression());
        expressions.put(Boolean.class, new BooleanTypeExpression());
    }

    /**
     * Set of possible numeric types.
     */
    private static final Set<Class> numberTypes = new HashSet<>();

    {
        numberTypes.add(Integer.class);
        numberTypes.add(Long.class);
    }

    /**
     * Constructor with params.
     *
     * @param specification Class that specifies the predicate.
     * @param search        String Search that contains the criteira that is being searched.
     */
    public SearchPredicate(Class<T> specification, String search) {
        super();
        this.specification = specification;
        this.search = search;
    }

    /**
     * Method that returns de Boolean Expression of the search.
     *
     * @return {@link BooleanExpression}
     */
    public BooleanExpression getExpression() {
        Pattern pattern = Pattern.compile(
                "(\\sAND\\s|\\sOR\\s|START\\s|,)(\\w+?)(!|::|:|<|>|<:|>:|\\?)(((?:\\w*\\.)+\\w+)|(\\w*[\\u002F\\u0024a-zA-Z0-9éúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄç]+))");
        Matcher matcher = pattern.matcher(search);
        BooleanExpression exp = null;
        while (matcher.find()) {
            String key = matcher.group(2);
            String valor = matcher.group(4);
            if (valor != null && valor.contains("$")) {
                valor = Arrays.stream(valor.split("\\u0024"))
                        .map(Object::toString)
                        .collect(Collectors.joining(" "));
            }

            if (!(isString(valor) && isTipoNumeric(getTypeField(key, specification)))) {
                String operation = matcher.group(1).trim();
                SearchCriteria criteria = new SearchCriteria(key, matcher.group(3), valor);
                LogicOperation logicOperation = operations.get(operation);
                exp = logicOperation.getOperation(exp, criteria);
            }
        }
        return exp;
    }

    /**
     * Map with all the possible operations.
     */
    static Map<String, LogicOperation> operations = new HashMap<>();

    {
        operations.put("START", new OperationStart());
        operations.put("AND", new OperationAND());
        operations.put(",", new OperationAND());
        operations.put("OR", new OperationOR());
    }

    /**
     * Interface that returns the actual operation.
     */
    interface LogicOperation {
        BooleanExpression getOperation(BooleanExpression exp, SearchCriteria criteria);
    }

    /**
     * Class that holds the actual operation and
     * implements the interface to get the actual operation.
     */
    class OperationStart implements LogicOperation {

        @Override
        public BooleanExpression getOperation(BooleanExpression exp, SearchCriteria criteria) {
            return getPredicate(criteria);
        }
    }

    /**
     * AND operation class.
     */
    class OperationAND implements LogicOperation {

        @Override
        public BooleanExpression getOperation(BooleanExpression exp, SearchCriteria criteria) {
            return exp.and(getPredicate(criteria));
        }
    }

    /**
     * OR operation class.
     */
    class OperationOR implements LogicOperation {

        @Override
        public BooleanExpression getOperation(BooleanExpression exp, SearchCriteria criteria) {
            return exp.or(getPredicate(criteria));
        }
    }

    /**
     * Method that returns the Object type of the search.
     *
     * @return {@link Class<?>}
     */
    private Class<?> getTypeField(String fieldName) {
        Class<T> specificationTemp = this.specification;
        while (specificationTemp != null) {
            for (Field field : specificationTemp.getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase(fieldName)) {
                    return field.getType();
                } else {
                    for (Field fieldSecondLevel : field.getType().getDeclaredFields()) {
                        if (fieldSecondLevel.getName().equalsIgnoreCase(fieldName)) {
                            return fieldSecondLevel.getType();
                        } else {
                            for (Field fieldThirdLevel : fieldSecondLevel.getType()
                                    .getDeclaredFields()) {
                                if (fieldThirdLevel.getName().equalsIgnoreCase(fieldName)) {
                                    return fieldThirdLevel.getType();
                                }
                            }
                        }
                    }
                }
            }
            specificationTemp = (Class<T>) specificationTemp.getSuperclass();
        }
        return null;
    }

    /**
     * Method that return what type of data is being search base on its field's type name.
     *
     * @param fieldName     Field name.
     * @param specification Class specified.
     * @return {@link Class}
     */
    private Class getTypeField(String fieldName, Class specification) {
        Class<T> specificationTemp = specification;
        while (specificationTemp != null) {
            for (Field field : specificationTemp.getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase(fieldName)) {
                    return field.getType();
                }
            }
            specificationTemp = (Class<T>) specificationTemp.getSuperclass();
        }
        return null;
    }

    /**
     * Method that returns the position of the actual seach.
     *
     * @param key      Search key.
     * @param position Position on the search.
     * @return {@link String}
     */
    private String getField(String key, Integer position) {
        return Arrays.asList(key.split("_")).get(position - 1);

    }

    /**
     * Method that returns if the search is a second level search.
     *
     * @param key Searched key.
     * @return {@link Boolean}
     */
    private Boolean isFieldSecondLevel(String key) {
        return Arrays.asList(key.split("_")).size() == 2;

    }

    /**
     * Method that returns if the search is a third level search.
     *
     * @param key Searched key.
     * @return {@link Boolean}
     */
    private Boolean isFieldThirdLevel(String key) {
        return Arrays.asList(key.split("_")).size() == 3;
    }

    /**
     * Method that returns if the search is a fourth level search.
     *
     * @param key Searched key.
     * @return {@link Boolean}
     */
    private Boolean isFieldFourthLevel(String key) {
        return Arrays.asList(key.split("_")).size() == 4;
    }

    /**
     * Method that returns the boolean expression of the actual search based on its level and criteria.
     *
     * @param criteria Criteria that's being searched.
     * @return {@link BooleanExpression}
     */
    private BooleanExpression getPredicate(SearchCriteria criteria) {
        String className = manualCapitalizeString(specification.getSimpleName());

        PathBuilder<T> entityPath = new PathBuilder<T>(specification, className);

        String key = criteria.getKey();
        PathBuilder<?> entityPathGeneric = entityPath;
        Class<?> specification = this.specification;

        if (isFieldSecondLevel(key)) {
            String compositeAttribute = getField(key, FIRST);
            Class compositeAttributeType = getTypeField(compositeAttribute);
            PathBuilder<Class> path = entityPath.get(compositeAttribute, compositeAttributeType);
            entityPathGeneric = path;
            key = getField(key, SECOND);
            specification = compositeAttributeType;
        }

        if (isFieldThirdLevel(key)) {
            String compositeAttribute = getField(key, FIRST);
            Class compositeAttributeType = getTypeField(compositeAttribute);
            PathBuilder<Class> path = entityPath.get(compositeAttribute, compositeAttributeType);

            String attThirdLevel = getField(key, SECOND);
            Class tipoThirdLevel = getTypeField(attThirdLevel, compositeAttributeType);
            PathBuilder<Class> pathThirdLevel = path.get(attThirdLevel, tipoThirdLevel);

            entityPathGeneric = pathThirdLevel;
            key = getField(key, THIRD);
            specification = tipoThirdLevel;
        }

        if (isFieldFourthLevel(key)) {
            String compositeAttribute = getField(key, FIRST);
            Class compositeAttributeType = getTypeField(compositeAttribute);
            PathBuilder<Class> path = entityPath.get(compositeAttribute, compositeAttributeType);

            String attThirdLevel = getField(key, SECOND);
            Class tipoThirdLevel = getTypeField(attThirdLevel, compositeAttributeType);
            PathBuilder<Class> pathThirdLevel = path.get(attThirdLevel, tipoThirdLevel);

            String attFourthLevel = getField(key, THIRD);
            Class tipoFourthLevel = getTypeField(attFourthLevel, tipoThirdLevel);
            PathBuilder<Class> pathFourthLevel = pathThirdLevel.get(attFourthLevel, tipoFourthLevel);

            entityPathGeneric = pathFourthLevel;
            key = getField(key, FOURTH);
            specification = tipoFourthLevel;
        }

        Class tipo = getTypeField(key, specification);
        Expression expression = tipo.isEnum() ? expressions.get(Enum.class) : expressions.get(tipo);

        String valor = criteria.getValue();
        return expression.getExpression(entityPathGeneric, tipo, key, criteria.getOperation(), valor);
    }

    /**
     * Method that returns if the tpye is numeric.
     *
     * @param specification Class that's being searched.
     * @return {@link Boolean}
     */
    private boolean isTipoNumeric(Class specification) {
        return numberTypes.contains(specification);
    }

    private boolean isString(String value) {
        try {
            Long.parseLong(value);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    /**
     * Method that manually capitalize the String, if needed.
     * @param className Name that's going to be capitalized.
     * @return {@link String}
     */
    private String manualCapitalizeString(String className) {
        String firstLetter = String.valueOf(className.charAt(0));

        char firstLetterLowerCase = firstLetter.toLowerCase().charAt(0);

        StringBuilder fixedName = new StringBuilder(className);
        fixedName.setCharAt(0, firstLetterLowerCase);

        return fixedName.toString();
    }

}

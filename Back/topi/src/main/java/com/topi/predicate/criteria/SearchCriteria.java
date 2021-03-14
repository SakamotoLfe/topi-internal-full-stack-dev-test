package com.topi.predicate.criteria;

/**
 * Class created to build the Search Criteria.
 *
 * @since 2021-02-23
 */

public class SearchCriteria {

    /**
     * Key that's being used on the search.
     */
    private String key;

    /**
     * Operation that's being used on the search.
     */
    private String operation;

    /**
     * Value that's being used on the search.
     */
    private String value;

    /* Constructors */

    /**
     * Empty constructor.
     */
    public SearchCriteria() {
        super();
    }

    /**
     * Constructor with params.
     *
     * @param key       Key that's being used on the search.
     * @param operation Operation that's being used on the search.
     * @param value     Value that's being used on the search.
     */
    public SearchCriteria(String key, String operation, String value) {
        super();
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    // Getters and Setters

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // To string

    /**
     * To String method.
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "SearchCriteria{" +
                "key='" + key + '\'' +
                ", operation='" + operation + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

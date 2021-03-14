package com.topi.predicate.impl;

import com.topi.model.Category;
import com.topi.predicate.criteria.SearchPredicate;

/**
 * Class created to implement the Search Predicate for Category.
 *
 * @since 2021-03-14
 */

public class CategoryPredicate extends SearchPredicate<Category> {

    /**
     * Constructor with params.
     *
     * @param search String Search that contains the criteira that is being searched.
     */
    public CategoryPredicate(String search) {
        super(Category.class, search);
    }
}

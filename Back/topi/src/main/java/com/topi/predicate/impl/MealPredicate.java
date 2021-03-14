package com.topi.predicate.impl;

import com.topi.model.Meal;
import com.topi.predicate.criteria.SearchPredicate;

/**
 * Class created to implement the Search Predicate for Meal.
 *
 * @since 2021-03-14
 */

public class MealPredicate extends SearchPredicate<Meal> {

    /**
     * Constructor with params.
     *
     * @param search String Search that contains the criteira that is being searched.
     */
    public MealPredicate(String search) {
        super(Meal.class, search);
    }
}

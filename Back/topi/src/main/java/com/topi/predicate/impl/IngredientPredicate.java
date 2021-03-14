package com.topi.predicate.impl;

import com.topi.model.Ingredient;
import com.topi.predicate.criteria.SearchPredicate;

/**
 * Class created to implement the Search Predicate for Ingredient.
 *
 * @since 2021-03-14
 */

public class IngredientPredicate extends SearchPredicate<Ingredient> {

    /**
     * Constructor with params.
     *
     * @param search String Search that contains the criteira that is being searched.
     */
    public IngredientPredicate(String search) {
        super(Ingredient.class, search);
    }
}

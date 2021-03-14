package com.topi.service;

import com.topi.model.Ingredient;
import com.topi.predicate.criteria.SearchPredicate;
import com.topi.predicate.impl.IngredientPredicate;
import com.topi.repository.BasicRepository;
import com.topi.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class created to be the Service of Ingredient.
 *
 * @since 2021-03-14
 */

@Service
public class IngredientService extends BasicService<Ingredient> {

    /**
     * Ingredient Repository Instance.
     */
    private IngredientRepository ingredientRepository;

    /**
     * Constructor with params.
     *
     * @param ingredientRepository Ingredient Repository Instance.
     */
    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Method that return to BasicService the Repository specified of this class.
     *
     * @return {@link BasicRepository<Ingredient>}. Repository specified on the runtime.
     */
    @Override
    protected BasicRepository<Ingredient> getBasicRepository() {
        return ingredientRepository;
    }

    /**
     * Method that returns to BasicService the Predicate specified of this class.
     *
     * @param search Criteria that's being searched.
     * @return {@link SearchPredicate<Ingredient>}. SearchPredicate specified on the runtime.
     */
    @Override
    protected SearchPredicate<Ingredient> getSearchPredicate(String search) {
        return new IngredientPredicate(search);
    }
}

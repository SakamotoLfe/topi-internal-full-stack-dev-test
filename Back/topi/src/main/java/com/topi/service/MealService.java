package com.topi.service;

import com.topi.model.Meal;
import com.topi.predicate.criteria.SearchPredicate;
import com.topi.predicate.impl.MealPredicate;
import com.topi.repository.BasicRepository;
import com.topi.repository.MealRepository;
import org.springframework.stereotype.Service;

/**
 * Class created to be the Service of Meal.
 *
 * @since 2021-03-14
 */

@Service
public class MealService extends BasicService<Meal> {

    /**
     * Meal Repository Instance.
     */
    private MealRepository mealRepository;

    /**
     * Constructor with params.
     *
     * @param mealRepository Meal Repository Instance.
     */
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    /**
     * Method that return to BasicService the Repository specified of this class.
     *
     * @return {@link BasicRepository<Meal>}. Repository specified on the runtime.
     */
    @Override
    protected BasicRepository<Meal> getBasicRepository() {
        return mealRepository;
    }

    /**
     * Method that returns to BasicService the Predicate specified of this class.
     *
     * @param search Criteria that's being searched.
     * @return {@link SearchPredicate<Meal>}. SearchPredicate specified on the runtime.
     */
    @Override
    protected SearchPredicate<Meal> getSearchPredicate(String search) {
        return new MealPredicate(search);
    }
}

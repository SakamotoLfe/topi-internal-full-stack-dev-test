package com.topi.controller;

import com.topi.model.Meal;
import com.topi.service.BasicService;
import com.topi.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class created to be the Controller of Meal.
 *
 * @since 2021-03-14
 */

@RestController
@RequestMapping("/meals")
public class MealController extends BasicController<Meal> {

    /**
     * Meal Repository Instance.
     */
    private MealService mealService;

    /**
     * Constructor with params.
     *
     * @param mealService Meal Repository Instance.
     */
    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    /**
     * Method that returns to BasicController the specified Service of this class.
     *
     * @return {@link BasicService<Meal>}. Specified Service of this class.
     */
    @Override
    protected BasicService<Meal> getBasicService() {
        return mealService;
    }
}

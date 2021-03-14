package com.topi.controller;

import com.topi.model.Category;
import com.topi.model.Ingredient;
import com.topi.service.BasicService;
import com.topi.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class created to be the Controller of Ingredient.
 *
 * @since 2021-03-14
 */

@RestController
@RequestMapping("/ingredients")
public class IngredientController extends BasicController<Ingredient> {

    /**
     * Ingredient Service Instance.
     */
    private IngredientService ingredientService;

    /**
     * Constructor with params.
     * @param ingredientService Ingredient Service Instance.
     */
    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    /**
     * Method that returns to BasicController the specified Service of this class.
     *
     * @return {@link BasicService<Ingredient>}. Specified Service of this class.
     */
    @Override
    protected BasicService<Ingredient> getBasicService() {
        return ingredientService;
    }
}
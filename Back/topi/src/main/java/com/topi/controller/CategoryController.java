package com.topi.controller;

import com.topi.model.Category;
import com.topi.service.BasicService;
import com.topi.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class created to be the Controller of Category.
 *
 * @since 2021-03-14
 */

@RestController
@RequestMapping("/categories")
public class CategoryController extends BasicController<Category> {

    /**
     * Category Service instance.
     */
    private CategoryService categoryService;

    /**
     * Constructor with params.
     *
     * @param categoryService Category Service instance.
     */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Method that returns to BasicController the specified Service of this class.
     *
     * @return {@link BasicService<Category>}. Specified Service of this class.
     */
    @Override
    protected BasicService<Category> getBasicService() {
        return categoryService;
    }
}

package com.topi.service;

import com.topi.model.Category;
import com.topi.predicate.criteria.SearchPredicate;
import com.topi.predicate.impl.CategoryPredicate;
import com.topi.repository.BasicRepository;
import com.topi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class created to be the Service of Category.
 *
 * @since 2021-03-14
 */

@Service
public class CategoryService extends BasicService<Category> {

    /**
     * Category Repository instance.
     */
    private CategoryRepository categoryRepository;

    /**
     * Constructor with params.
     *
     * @param categoryRepository Category Repository instance.
     */
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Method that return to BasicService the Repository specified of this class.
     *
     * @return {@link BasicRepository<Category>}. Repository specified on the runtime.
     */
    @Override
    protected BasicRepository<Category> getBasicRepository() {
        return categoryRepository;
    }

    /**
     * Method that returns to BasicService the Predicate specified of this class.
     *
     * @param search Criteria that's being searched.
     * @return {@link SearchPredicate<Category>}. SearchPredicate specified on the runtime.
     */
    @Override
    protected SearchPredicate<Category> getSearchPredicate(String search) {
        return new CategoryPredicate(search);
    }
}

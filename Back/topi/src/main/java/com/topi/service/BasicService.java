package com.topi.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.topi.exceptions.NotFoundException;
import com.topi.exceptions.ApiErrorResponse;
import com.topi.model.BasicEntity;
import com.topi.predicate.criteria.SearchPredicate;
import com.topi.repository.BasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

/**
 * Class created to be a Basic Service for the entire system.
 *
 * @since 2021-02-23
 */

@Service
public abstract class BasicService<T extends BasicEntity> {

    /**
     * Method that specifies the repository in runtime.
     *
     * @return {@link BasicRepository<T>}. Basic Repository.
     */
    @Autowired
    protected abstract BasicRepository<T> getBasicRepository();

    /**
     * Method that specifies the Predicate class that's being used in Runtime.
     *
     * @return {@link SearchPredicate<T>}. Basic Search Predicate.
     */
    @Autowired
    protected abstract SearchPredicate<T> getSearchPredicate(String search);

    /**
     * Method that effectively saves an basic object.
     *
     * @param obj Object that's being saved.
     * @return {@link T}. Saved object.
     */
    public T save(@RequestBody T obj) {
        return getBasicRepository().save(obj);
    }

    /**
     * Method that effectively update an basic object.
     *
     * @param obj Object that's being updated.
     * @return {@link T}. Updated object.
     */
    public T update(@PathVariable Long id, @RequestBody final T obj) {
        if (getBasicRepository().existsById(id)) {
            return getBasicRepository().save(obj);
        } else {
            throw new NotFoundException("Object not found in database!");
        }
    }

    /**
     * Method that effectively deactivate an object.
     *
     * @param id ID of the object that's being deleted.
     * @return {@link T}. Deactivated object.
     */
    public T delete(@PathVariable Long id) {
        Optional<T> obj = getBasicRepository().findById(id);
        if (obj.isPresent()) {
            obj.get().setEnabled(false);
            return getBasicRepository().save(obj.get());
        } else {
            return null;
        }
    }

    /**
     * Method that effectively searches objects with a given
     * criteria via Search Predicate Regex.
     *
     * @param pageable   Object that configures the pageable options.
     * @param search     Criteria that's being searched.
     * @param isPageable Flag that says if the result needs to be paged.
     * @return {@link Page<T>}. Pages with the result of the searched criteria.
     */
    public Page<T> search(Pageable pageable, String search, Boolean isPageable) {
        if (isPageable) {
            if (!search.isEmpty()) {
                BooleanExpression expression = getSearchPredicate(search).getExpression();
                return new PageImpl<>((List<T>) getBasicRepository().findAll(expression));
            }
            return new PageImpl<>(getBasicRepository().findAll());
        }
        return searchNoPaged(pageable, search);
    }

    /**
     * Método que de fato efetua a pesquisa do Search Predicate.
     *
     * @param pageable   Object that configures the pageable options.
     * @param search     Criteria that's being searched.
     * @return {@link Page<T>}. Pages with the result of the searched criteria.
     * Here you'll have a single page with all the data.
     */
    private Page<T> searchNoPaged(Pageable pageable, String search) {
        if (search == null || search.isEmpty()) {
            return getBasicRepository().findAll(pageable);
        }
        try {
            BooleanExpression expression = getSearchPredicate(search).getExpression();
            return getBasicRepository().findAll(expression, pageable);
        } catch (NullPointerException e) {
            throw new ApiErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, 4022,
                    "The criteria is invalid!");
        }
    }

    /**
     * Method that effectively searches an object based on its ID.
     *
     * @param id Object's ID.
     * @return {@link T}. Result of the search.
     */
    public T loadById(@PathVariable("id") Long id) {
        return getBasicRepository().findById(id).orElseGet(() -> {
            throw new NotFoundException("That ID doesn't correspond to any entries on the database.");
        });
    }
}

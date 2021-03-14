package com.topi.predicate.impl;

import com.topi.model.Tag;
import com.topi.predicate.criteria.SearchPredicate;

/**
 * Class created to implement the Search Predicate for Tag.
 *
 * @since 2021-03-14
 */

public class TagPredicate extends SearchPredicate<Tag> {

    /**
     * Constructor with params.
     *
     * @param search String Search that contains the criteira that is being searched.
     */
    public TagPredicate(String search) {
        super(Tag.class, search);
    }
}

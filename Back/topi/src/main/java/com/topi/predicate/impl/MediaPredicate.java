package com.topi.predicate.impl;

import com.topi.model.Media;
import com.topi.predicate.criteria.SearchPredicate;

/**
 * Class created to implement the Search Predicate for Media.
 *
 * @since 2021-03-14
 */

public class MediaPredicate extends SearchPredicate<Media> {

    /**
     * Constructor with params.
     *
     * @param search String Search that contains the criteira that is being searched.
     */
    public MediaPredicate(String search) {
        super(Media.class, search);
    }
}

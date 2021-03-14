package com.topi.service;

import com.topi.model.Tag;
import com.topi.predicate.criteria.SearchPredicate;
import com.topi.predicate.impl.TagPredicate;
import com.topi.repository.BasicRepository;
import com.topi.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class created to be the Service of Tag.
 *
 * @since 2021-03-14
 */

@Service
public class TagService extends BasicService<Tag> {

    /**
     * Tag Repository Instance.
     */
    private TagRepository tagRepository;

    /**
     * constructor with params.
     *
     * @param tagRepository Tag Repository Instance.
     */
    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * Method that return to BasicService the Repository specified of this class.
     *
     * @return {@link BasicRepository<Tag>}. Repository specified on the runtime.
     */
    @Override
    protected BasicRepository<Tag> getBasicRepository() {
        return tagRepository;
    }

    /**
     * Method that returns to BasicService the Predicate specified of this class.
     *
     * @param search Criteria that's being searched.
     * @return {@link SearchPredicate<Tag>}. SearchPredicate specified on the runtime.
     */
    @Override
    protected SearchPredicate<Tag> getSearchPredicate(String search) {
        return new TagPredicate(search);
    }
}

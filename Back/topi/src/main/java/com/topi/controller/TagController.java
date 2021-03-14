package com.topi.controller;

import com.topi.model.Tag;
import com.topi.service.BasicService;
import com.topi.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class created to be the Controller of Tag.
 *
 * @since 2021-03-14
 */

@RestController
@RequestMapping("/tags")
public class TagController extends BasicController<Tag> {

    /**
     * Tag Service Instance.
     */
    private TagService tagService;

    /**
     * Constructor with params.
     *
     * @param tagService Tag Service Instance.
     */
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Method that returns to BasicController the specified Service of this class.
     *
     * @return {@link BasicService<Tag>}. Specified Service of this class.
     */
    @Override
    protected BasicService<Tag> getBasicService() {
        return tagService;
    }
}

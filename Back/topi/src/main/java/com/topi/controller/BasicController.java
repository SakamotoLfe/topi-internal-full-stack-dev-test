package com.topi.controller;


import com.topi.model.BasicEntity;
import com.topi.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

/**
 * Class created to be the Basic Controller for the system.
 *
 * @since 2021-02-23
 */

@Controller
public abstract class BasicController<T extends BasicEntity> {

    /**
     * Method that returns the specific controller in runtime
     *
     * @return {@link BasicService<T>}. Basic Service.
     */
    @Autowired
    protected abstract BasicService<T> getBasicService();

    /**
     * Method that saves a generic object.
     * This object is specified at runtime.
     *
     * @param obj Objeto a ser salvo.
     * @return {@link ResponseEntity <T>}. Saved entity.
     */
    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> save(@RequestBody T obj) {
        return new ResponseEntity<>(getBasicService().save(obj), HttpStatus.CREATED);
    }

    /**
     * Method that updates a generic object.
     * This object is specified at runtime.
     *
     * @param obj Updated entity.
     * @return {@link ResponseEntity<T>}. Updated entity.
     */
    @ResponseBody
    @PutMapping(value = "/{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody final T obj) {
        return new ResponseEntity<>(getBasicService().update(id, obj), HttpStatus.OK);
    }

    /**
     * Method that returns an object base on its id.
     * This object is specified at runtime.
     *
     * @param id Object's id that's being searched.
     * @return {@link T}. Entity with the called ID.
     */
    @ResponseBody
    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<T> loadById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(getBasicService().loadById(id), HttpStatus.OK);
    }

    /**
     * Method that deactivate an object base on its id.
     * This object is specified at runtime.
     *
     * @param id Object's id that's being removed.
     * @return {@link ResponseEntity<T>}. Deactivated entity.
     */
    @ResponseBody
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<T> delete(@PathVariable Long id) {
        Optional<T> obj = Optional.ofNullable(getBasicService().delete(id));
        return obj.map(result -> new ResponseEntity<>(getBasicService().save(result), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    /**
     * Basic method that makes basic searches to
     * make it easy basic calls from external connections.
     * You just need something that looks like this:
     * "START id:1"
     * OR
     * "START tag_name?Car"
     * The generic object is specified at runtime.
     *
     * @param pageable   Pageable configuration object.
     * @param search     String with the search that's being made.
     * @param isPageable If the result should be paged or not.
     * @return {@link ResponseEntity<Page<T>>}. Pages with the result.
     */
    @ResponseBody
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Page<T>> search(Pageable pageable,
                                          @RequestParam(required = false, value = "search") String search,
                                          @RequestParam(value = "isPageable", defaultValue = "false")
                                                  Boolean isPageable) {
        return new ResponseEntity<>(getBasicService().search(pageable, search, isPageable), HttpStatus.OK);
    }
}

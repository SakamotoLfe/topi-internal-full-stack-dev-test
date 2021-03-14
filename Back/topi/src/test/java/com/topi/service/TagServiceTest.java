package com.topi.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.topi.exceptions.NotFoundException;
import com.topi.model.Tag;
import com.topi.repository.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TagServiceTest {

    @MockBean
    private TagRepository tagRepository;

    TagService tagService;

    Tag tag = new Tag(1L, new Date(), null, true, "Name", null);

    Pageable pageable;

    @BeforeEach
    void setup(){
        tagRepository = Mockito.mock(TagRepository.class);
        tagService = new TagService(tagRepository);
        tag.setId(1L);
        pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("nome"), Sort.Order.desc("id")));
    }

    @Test
    void save() {
        Mockito.when(tagRepository.save(Mockito.any())).thenReturn(tag);
        Assertions.assertNotNull(tagService.save(tag));
    }

    @Test
    void update() {
        Mockito.when(tagRepository.existsById(Mockito.any())).thenReturn(false);
        Mockito.when(tagRepository.save(Mockito.any())).thenReturn(tag);
        Assertions.assertThrows(NotFoundException.class, () -> tagService.update(tag.getId(), tag));

        Mockito.when(tagRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.when(tagRepository.save(Mockito.any())).thenReturn(tag);
        Assertions.assertDoesNotThrow(() -> tagService.update(tag.getId(), tag));
        Assertions.assertNotNull(tagService.update(tag.getId(), tag));
    }

    @Test
    void delete() {
        Mockito.when(tagRepository.findById(Mockito.any())).thenReturn(Optional.of(tag));
        Mockito.when(tagRepository.save(Mockito.any())).thenReturn(tag);
        Assertions.assertNotNull(tagService.delete(tag.getId()));

        Mockito.when(tagRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(tagRepository.save(Mockito.any())).thenReturn(null);
        Assertions.assertNull(tagService.delete(tag.getId()));
    }

    @Test
    void search() {
        Mockito.when(tagRepository.findAll(Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(tag)));
        Mockito.when(tagRepository.findAll(Mockito.any(BooleanExpression.class), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(tag)));
        Mockito.when(tagRepository.findAll()).thenReturn(Collections.singletonList(tag));
        Assertions.assertNotNull(tagService.search(pageable, "START id:1", true));
        Assertions.assertNotNull(tagService.search(pageable, "START id:1", false));
        Assertions.assertNotNull(tagService.search(pageable, "", true));
        Assertions.assertNotNull(tagService.search(pageable, "", false));
    }

    @Test
    void loadById() {
        Mockito.when(tagRepository.findById(Mockito.any())).thenReturn(Optional.of(tag));
        Assertions.assertNotNull(tagService.loadById(tag.getId()));

        Mockito.when(tagRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> tagService.loadById(tag.getId()));
    }

    @Test
    void getBasicRepository() {
        Assertions.assertNotNull(tagService.getBasicRepository());
    }

    @Test
    void getSearchPredicate() {
        Assertions.assertNotNull(tagService.getSearchPredicate(""));
    }
}
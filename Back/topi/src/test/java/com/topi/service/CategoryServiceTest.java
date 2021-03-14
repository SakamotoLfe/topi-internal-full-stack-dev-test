package com.topi.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.topi.exceptions.NotFoundException;
import com.topi.model.Category;
import com.topi.repository.CategoryRepository;
import org.hibernate.ObjectNotFoundException;
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

class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    CategoryService categoryService;

    Category category = new Category(1L, new Date(), null, true, "Name", null);

    Pageable pageable;

    @BeforeEach
    void setup(){
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryService = new CategoryService(categoryRepository);
        category.setId(1L);
        pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("nome"), Sort.Order.desc("id")));
    }

    @Test
    void save() {
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(category);
        Assertions.assertNotNull(categoryService.save(category));
    }

    @Test
    void update() {
        Mockito.when(categoryRepository.existsById(Mockito.any())).thenReturn(false);
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(category);
        Assertions.assertThrows(NotFoundException.class, () -> categoryService.update(category.getId(), category));

        Mockito.when(categoryRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(category);
        Assertions.assertDoesNotThrow(() -> categoryService.update(category.getId(), category));
        Assertions.assertNotNull(categoryService.update(category.getId(), category));
    }

    @Test
    void delete() {
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(category);
        Assertions.assertNotNull(categoryService.delete(category.getId()));

        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(null);
        Assertions.assertNull(categoryService.delete(category.getId()));
    }

    @Test
    void search() {
        Mockito.when(categoryRepository.findAll(Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(category)));
        Mockito.when(categoryRepository.findAll(Mockito.any(BooleanExpression.class), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(category)));
        Mockito.when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));
        Assertions.assertNotNull(categoryService.search(pageable, "START id:1", true));
        Assertions.assertNotNull(categoryService.search(pageable, "START id:1", false));
        Assertions.assertNotNull(categoryService.search(pageable, "", true));
        Assertions.assertNotNull(categoryService.search(pageable, "", false));
    }

    @Test
    void loadById() {
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(category));
        Assertions.assertNotNull(categoryService.loadById(category.getId()));

        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> categoryService.loadById(category.getId()));
    }

    @Test
    void getBasicRepository() {
        Assertions.assertNotNull(categoryService.getBasicRepository());
    }

    @Test
    void getSearchPredicate() {
        Assertions.assertNotNull(categoryService.getSearchPredicate(""));
    }
}
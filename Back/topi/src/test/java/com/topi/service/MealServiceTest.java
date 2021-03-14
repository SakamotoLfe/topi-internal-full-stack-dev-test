package com.topi.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.topi.exceptions.NotFoundException;
import com.topi.model.Category;
import com.topi.model.Ingredient;
import com.topi.model.Meal;
import com.topi.model.Media;
import com.topi.model.Tag;
import com.topi.repository.MealRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MealServiceTest {

    @MockBean
    private MealRepository mealRepository;

    MealService mealService;

    Meal meal = new Meal(1L, new Date(), null, true, "Meal", new Category(), "Area",
            new ArrayList<>(), null, new Media(), new ArrayList<>(), new ArrayList<>());

    Pageable pageable;

    @BeforeEach
    void setup(){
        mealRepository = Mockito.mock(MealRepository.class);
        mealService = new MealService(mealRepository);
        meal.setId(1L);
        pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("nome"), Sort.Order.desc("id")));
    }

    @Test
    void save() {
        Mockito.when(mealRepository.save(Mockito.any())).thenReturn(meal);
        Assertions.assertNotNull(mealService.save(meal));
    }

    @Test
    void update() {
        Mockito.when(mealRepository.existsById(Mockito.any())).thenReturn(false);
        Mockito.when(mealRepository.save(Mockito.any())).thenReturn(meal);
        Assertions.assertThrows(NotFoundException.class, () -> mealService.update(meal.getId(), meal));

        Mockito.when(mealRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.when(mealRepository.save(Mockito.any())).thenReturn(meal);
        Assertions.assertDoesNotThrow(() -> mealService.update(meal.getId(), meal));
        Assertions.assertNotNull(mealService.update(meal.getId(), meal));
    }

    @Test
    void delete() {
        Mockito.when(mealRepository.findById(Mockito.any())).thenReturn(Optional.of(meal));
        Mockito.when(mealRepository.save(Mockito.any())).thenReturn(meal);
        Assertions.assertNotNull(mealService.delete(meal.getId()));

        Mockito.when(mealRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(mealRepository.save(Mockito.any())).thenReturn(null);
        Assertions.assertNull(mealService.delete(meal.getId()));
    }

    @Test
    void search() {
        Mockito.when(mealRepository.findAll(Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(meal)));
        Mockito.when(mealRepository.findAll(Mockito.any(BooleanExpression.class), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(meal)));
        Mockito.when(mealRepository.findAll()).thenReturn(Collections.singletonList(meal));
        Assertions.assertNotNull(mealService.search(pageable, "START id:1", true));
        Assertions.assertNotNull(mealService.search(pageable, "START id:1", false));
        Assertions.assertNotNull(mealService.search(pageable, "", true));
        Assertions.assertNotNull(mealService.search(pageable, "", false));
    }

    @Test
    void loadById() {
        Mockito.when(mealRepository.findById(Mockito.any())).thenReturn(Optional.of(meal));
        Assertions.assertNotNull(mealService.loadById(meal.getId()));

        Mockito.when(mealRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> mealService.loadById(meal.getId()));
    }

    @Test
    void getBasicRepository() {
        Assertions.assertNotNull(mealService.getBasicRepository());
    }

    @Test
    void getSearchPredicate() {
        Assertions.assertNotNull(mealService.getSearchPredicate(""));
    }
}
package com.topi.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.topi.exceptions.NotFoundException;
import com.topi.model.Ingredient;
import com.topi.repository.IngredientRepository;
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

class IngredientServiceTest {

    @MockBean
    private IngredientRepository ingredientRepository;

    IngredientService ingredientService;

    Ingredient ingredient = new Ingredient(1L, new Date(), null, true, "Name", "1 cup");

    Pageable pageable;

    @BeforeEach
    void setup(){
        ingredientRepository = Mockito.mock(IngredientRepository.class);
        ingredientService = new IngredientService(ingredientRepository);
        ingredient.setId(1L);
        pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("nome"), Sort.Order.desc("id")));
    }

    @Test
    void save() {
        Mockito.when(ingredientRepository.save(Mockito.any())).thenReturn(ingredient);
        Assertions.assertNotNull(ingredientService.save(ingredient));
    }

    @Test
    void update() {
        Mockito.when(ingredientRepository.existsById(Mockito.any())).thenReturn(false);
        Mockito.when(ingredientRepository.save(Mockito.any())).thenReturn(ingredient);
        Assertions.assertThrows(NotFoundException.class, () -> ingredientService.update(ingredient.getId(), ingredient));

        Mockito.when(ingredientRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.when(ingredientRepository.save(Mockito.any())).thenReturn(ingredient);
        Assertions.assertDoesNotThrow(() -> ingredientService.update(ingredient.getId(), ingredient));
        Assertions.assertNotNull(ingredientService.update(ingredient.getId(), ingredient));
    }

    @Test
    void delete() {
        Mockito.when(ingredientRepository.findById(Mockito.any())).thenReturn(Optional.of(ingredient));
        Mockito.when(ingredientRepository.save(Mockito.any())).thenReturn(ingredient);
        Assertions.assertNotNull(ingredientService.delete(ingredient.getId()));

        Mockito.when(ingredientRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(ingredientRepository.save(Mockito.any())).thenReturn(null);
        Assertions.assertNull(ingredientService.delete(ingredient.getId()));
    }

    @Test
    void search() {
        Mockito.when(ingredientRepository.findAll(Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(ingredient)));
        Mockito.when(ingredientRepository.findAll(Mockito.any(BooleanExpression.class), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(ingredient)));
        Mockito.when(ingredientRepository.findAll()).thenReturn(Collections.singletonList(ingredient));
        Assertions.assertNotNull(ingredientService.search(pageable, "START id:1", true));
        Assertions.assertNotNull(ingredientService.search(pageable, "START id:1", false));
        Assertions.assertNotNull(ingredientService.search(pageable, "", true));
        Assertions.assertNotNull(ingredientService.search(pageable, "", false));
    }

    @Test
    void loadById() {
        Mockito.when(ingredientRepository.findById(Mockito.any())).thenReturn(Optional.of(ingredient));
        Assertions.assertNotNull(ingredientService.loadById(ingredient.getId()));

        Mockito.when(ingredientRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> ingredientService.loadById(ingredient.getId()));
    }

    @Test
    void getBasicRepository() {
        Assertions.assertNotNull(ingredientService.getBasicRepository());
    }

    @Test
    void getSearchPredicate() {
        Assertions.assertNotNull(ingredientService.getSearchPredicate(""));
    }
}
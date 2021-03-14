package com.topi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.topi.model.Ingredient;
import com.topi.repository.IngredientRepository;
import com.topi.service.IngredientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class IngredientControllerTest {

    IngredientController ingredientController;

    @MockBean
    IngredientService ingredientService;

    @MockBean
    IngredientRepository ingredientRepository;

    Ingredient ingredient1 = new Ingredient(1L, new Date(), null, true, "Salt", "Pinch");
    Ingredient ingredient2 = new Ingredient("Sugar", "1 cup");

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        ingredientService = Mockito.mock(IngredientService.class);
        ingredientRepository = Mockito.mock(IngredientRepository.class);
        ingredientController = new IngredientController(ingredientService);
        ingredient2.setId(2L);
        ingredient2.setEnabled(true);
    }

    @Test
    void save() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
        Mockito.when(ingredientRepository.save(Mockito.any(Ingredient.class)))
                .thenReturn(ingredient1);

        mockMvc.perform(MockMvcRequestBuilders.post("/ingredients")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(ingredient1)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void update() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
        Mockito.when(ingredientRepository.existsById(Mockito.anyLong()))
                .thenReturn(true);
        Mockito.when(ingredientRepository.save(Mockito.any(Ingredient.class)))
                .thenReturn(ingredient1);

        mockMvc.perform(MockMvcRequestBuilders.put("/ingredients/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(ingredient1))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void loadById() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
        Mockito.when(ingredientRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(ingredient1));

        mockMvc.perform(MockMvcRequestBuilders.get("/ingredients/{id}", ingredient1.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void delete() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
        ingredient1.setEnabled(false);
        Mockito.when(ingredientRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(ingredient1));
        Mockito.when(ingredientRepository.save(Mockito.any(Ingredient.class))).thenReturn(ingredient1);
        Mockito.when(ingredientService.delete(Mockito.anyLong())).thenReturn(ingredient1);
        Mockito.when(ingredientService.save(Mockito.any(Ingredient.class))).thenReturn(ingredient1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/ingredients/{id}", ingredient1.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.enabled", is(false)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void search() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView()).build();
        Mockito.when(ingredientRepository.findAll()).thenReturn(Arrays.asList(ingredient1, ingredient2));
        requestWithParams("", false, ingredient1, ingredient2);
        requestWithParams("START id:1", false, ingredient1, ingredient2);
    }

    void requestWithParams(String search, boolean pageable, Ingredient obj1, Ingredient obj2) throws Exception {
        String url = "?isPageable=" + pageable + ((search != null && !search.isEmpty()) ? "&search=" + search : "");
        mockMvc.perform(MockMvcRequestBuilders.get("/ingredients" + url))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getBasicService() {
        Assertions.assertNotNull(ingredientController.getBasicService());
    }
}
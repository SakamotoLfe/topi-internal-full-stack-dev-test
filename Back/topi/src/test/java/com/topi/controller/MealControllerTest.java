package com.topi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.topi.model.Category;
import com.topi.model.Ingredient;
import com.topi.model.Instruction;
import com.topi.model.Meal;
import com.topi.model.Media;
import com.topi.model.Tag;
import com.topi.repository.MealRepository;
import com.topi.service.MealService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MealControllerTest {

    MealController mealController;

    @MockBean
    MealService mealService;

    @MockBean
    MealRepository mealRepository;

    Meal meal1 = new Meal(1L, new Date(), null, true, "Kebab", new Category(), "Arabia",
            new ArrayList<>(), null, null, new ArrayList<>(), new ArrayList<>());
    Meal meal2 = new Meal("Cake", new Category(), "EUA", new ArrayList<>(), null, null,
            new ArrayList<>(), new ArrayList<>());

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mealService = Mockito.mock(MealService.class);
        mealRepository = Mockito.mock(MealRepository.class);
        mealController = new MealController(mealService);
        meal2.setId(2L);
        meal2.setEnabled(true);
    }

    @Test
    void save() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(mealController).build();
        Mockito.when(mealRepository.save(Mockito.any(Meal.class)))
                .thenReturn(meal1);

        mockMvc.perform(MockMvcRequestBuilders.post("/meals")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(meal1)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void update() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(mealController).build();
        Mockito.when(mealRepository.existsById(Mockito.anyLong()))
                .thenReturn(true);
        Mockito.when(mealRepository.save(Mockito.any(Meal.class)))
                .thenReturn(meal1);

        mockMvc.perform(MockMvcRequestBuilders.put("/meals/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(meal1))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void loadById() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(mealController).build();
        Mockito.when(mealRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(meal1));

        mockMvc.perform(MockMvcRequestBuilders.get("/meals/{id}", meal1.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void delete() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(mealController).build();
        meal1.setEnabled(false);
        Mockito.when(mealRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(meal1));
        Mockito.when(mealRepository.save(Mockito.any(Meal.class))).thenReturn(meal1);
        Mockito.when(mealService.delete(Mockito.anyLong())).thenReturn(meal1);
        Mockito.when(mealService.save(Mockito.any(Meal.class))).thenReturn(meal1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/meals/{id}", meal1.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.enabled", is(false)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void search() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(mealController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView()).build();
        Mockito.when(mealRepository.findAll()).thenReturn(Arrays.asList(meal1, meal2));
        requestWithParams("", false, meal1, meal2);
        requestWithParams("START id:1", false, meal1, meal2);
    }

    void requestWithParams(String search, boolean pageable, Meal obj1, Meal obj2) throws Exception {
        String url = "?isPageable=" + pageable + ((search != null && !search.isEmpty()) ? "&search=" + search : "");
        mockMvc.perform(MockMvcRequestBuilders.get("/meals" + url))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getBasicService() {
        Assertions.assertNotNull(mealController.getBasicService());
    }
}
package com.topi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.topi.model.Category;
import com.topi.repository.CategoryRepository;
import com.topi.service.CategoryService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest {

    CategoryController categoryController;

    @MockBean
    CategoryService categoryService;

    @MockBean
    CategoryRepository categoryRepository;

    Category category1 = new Category(1L, new Date(), null, true, "Salty", "");
    Category category2 = new Category("Sweets", "");

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        categoryService = Mockito.mock(CategoryService.class);
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryController = new CategoryController(categoryService);
        category2.setId(2L);
        category2.setEnabled(true);
    }

    @Test
    void save() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
        Mockito.when(categoryRepository.save(Mockito.any(Category.class)))
                .thenReturn(category1);

        mockMvc.perform(MockMvcRequestBuilders.post("/categories")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(category1)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void update() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
        Mockito.when(categoryRepository.existsById(Mockito.anyLong()))
                .thenReturn(true);
        Mockito.when(categoryRepository.save(Mockito.any(Category.class)))
                .thenReturn(category1);

        mockMvc.perform(MockMvcRequestBuilders.put("/categories/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(category1))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void loadById() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
        Mockito.when(categoryRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(category1));

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/{id}", category1.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void delete() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
        category1.setEnabled(false);
        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(category1));
        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category1);
        Mockito.when(categoryService.delete(Mockito.anyLong())).thenReturn(category1);
        Mockito.when(categoryService.save(Mockito.any(Category.class))).thenReturn(category1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/categories/{id}", category1.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.enabled", is(false)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void search() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView()).build();
        Mockito.when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));
        requestWithParams("", false, category1, category2);
        requestWithParams("START id:1", false, category1, category2);
    }

    void requestWithParams(String search, boolean pageable, Category obj1, Category obj2) throws Exception {
        String url = "?isPageable=" + pageable + ((search != null && !search.isEmpty()) ? "&search=" + search : "");
        mockMvc.perform(MockMvcRequestBuilders.get("/categories" + url))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getBasicService() {
        Assertions.assertNotNull(categoryController.getBasicService());
    }
}
package com.topi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.topi.model.Tag;
import com.topi.repository.TagRepository;
import com.topi.service.TagService;
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

class TagControllerTest {

    TagController tagController;

    @MockBean
    TagService tagService;

    @MockBean
    TagRepository tagRepository;

    Tag tag1 = new Tag(1L, new Date(), null, true, "Cars", null);
    Tag tag2 = new Tag("Food", null);

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        tagService = Mockito.mock(TagService.class);
        tagRepository = Mockito.mock(TagRepository.class);
        tagController = new TagController(tagService);
        tag2.setId(2L);
        tag2.setEnabled(true);
    }

    @Test
    void save() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();
        Mockito.when(tagRepository.save(Mockito.any(Tag.class)))
                .thenReturn(tag1);

        mockMvc.perform(MockMvcRequestBuilders.post("/tags")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(tag1)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void update() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();
        Mockito.when(tagRepository.existsById(Mockito.anyLong()))
                .thenReturn(true);
        Mockito.when(tagRepository.save(Mockito.any(Tag.class)))
                .thenReturn(tag1);

        mockMvc.perform(MockMvcRequestBuilders.put("/tags/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(tag1))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void loadById() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();
        Mockito.when(tagRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(tag1));

        mockMvc.perform(MockMvcRequestBuilders.get("/tags/{id}", tag1.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void delete() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();
        tag1.setEnabled(false);
        Mockito.when(tagRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(tag1));
        Mockito.when(tagRepository.save(Mockito.any(Tag.class))).thenReturn(tag1);
        Mockito.when(tagService.delete(Mockito.anyLong())).thenReturn(tag1);
        Mockito.when(tagService.save(Mockito.any(Tag.class))).thenReturn(tag1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/tags/{id}", tag1.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.enabled", is(false)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void search() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(tagController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView()).build();
        Mockito.when(tagRepository.findAll()).thenReturn(Arrays.asList(tag1, tag2));
        requestWithParams("", false, tag1, tag2);
        requestWithParams("START id:1", false, tag1, tag2);
    }

    void requestWithParams(String search, boolean pageable, Tag obj1, Tag obj2) throws Exception {
        String url = "?isPageable=" + pageable + ((search != null && !search.isEmpty()) ? "&search=" + search : "");
        mockMvc.perform(MockMvcRequestBuilders.get("/tags" + url))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getBasicService() {
        Assertions.assertNotNull(tagController.getBasicService());
    }
}
package com.topi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topi.model.Category;
import com.topi.model.Media;
import com.topi.service.MediaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MediaControllerTest {
    
    MediaController mediaController;
    
    @MockBean
    MediaService mediaService;

    Media media = new Media(1L, new Date(), null, true, "png", "", 10F,
            "name", null);
    
    @BeforeEach
    void setup(){
        mediaService = Mockito.mock(MediaService.class);
        mediaController = new MediaController(mediaService);
    }

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

//    @Test
//    void save() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(mediaController).build();
//        Mockito.when(mediaService.saveFile(Mockito.any()))
//                .thenReturn(true);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/medias/upload")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(objectMapper.writeValueAsString(media)))
//                .andExpect(status().isCreated())
//                .andDo(print());
//    }

    @Test
    void loadById() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(mediaController).build();
        Mockito.when(mediaService.getMedia(Mockito.anyLong()))
                .thenReturn(media);

        mockMvc.perform(MockMvcRequestBuilders.get("/medias/download/{id}", media.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
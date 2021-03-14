package com.topi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.topi.model.Instruction;
import com.topi.repository.InstructionRepository;
import com.topi.service.InstructionService;
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

class InstructionControllerTest {

    InstructionController instructionController;

    @MockBean
    InstructionService instructionService;

    @MockBean
    InstructionRepository instructionRepository;

    Instruction instruction1 = new Instruction(1L, new Date(), null, true,
            "Put the suga on the can", 1, 1.5F);
    Instruction instruction2 = new Instruction("Mix with the salt", 2, 3F);

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        instructionService = Mockito.mock(InstructionService.class);
        instructionRepository = Mockito.mock(InstructionRepository.class);
        instructionController = new InstructionController(instructionService);
        instruction2.setId(2L);
        instruction2.setEnabled(true);
    }

    @Test
    void save() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(instructionController).build();
        Mockito.when(instructionRepository.save(Mockito.any(Instruction.class)))
                .thenReturn(instruction1);

        mockMvc.perform(MockMvcRequestBuilders.post("/instructions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(instruction1)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void update() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(instructionController).build();
        Mockito.when(instructionRepository.existsById(Mockito.anyLong()))
                .thenReturn(true);
        Mockito.when(instructionRepository.save(Mockito.any(Instruction.class)))
                .thenReturn(instruction1);

        mockMvc.perform(MockMvcRequestBuilders.put("/instructions/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(instruction1))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void loadById() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(instructionController).build();
        Mockito.when(instructionRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(instruction1));

        mockMvc.perform(MockMvcRequestBuilders.get("/instructions/{id}", instruction1.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void delete() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(instructionController).build();
        instruction1.setEnabled(false);
        Mockito.when(instructionRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(instruction1));
        Mockito.when(instructionRepository.save(Mockito.any(Instruction.class))).thenReturn(instruction1);
        Mockito.when(instructionService.delete(Mockito.anyLong())).thenReturn(instruction1);
        Mockito.when(instructionService.save(Mockito.any(Instruction.class))).thenReturn(instruction1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/instructions/{id}", instruction1.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.enabled", is(false)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void search() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(instructionController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView()).build();
        Mockito.when(instructionRepository.findAll()).thenReturn(Arrays.asList(instruction1, instruction2));
        requestWithParams("", false, instruction1, instruction2);
        requestWithParams("START id:1", false, instruction1, instruction2);
    }

    void requestWithParams(String search, boolean pageable, Instruction obj1, Instruction obj2) throws Exception {
        String url = "?isPageable=" + pageable + ((search != null && !search.isEmpty()) ? "&search=" + search : "");
        mockMvc.perform(MockMvcRequestBuilders.get("/instructions" + url))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getBasicService() {
        Assertions.assertNotNull(instructionController.getBasicService());
    }
}
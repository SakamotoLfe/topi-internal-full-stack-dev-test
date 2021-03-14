package com.topi.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.topi.exceptions.NotFoundException;
import com.topi.model.Instruction;
import com.topi.repository.InstructionRepository;
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

class InstructionServiceTest {

    @MockBean
    private InstructionRepository instructionRepository;

    InstructionService instructionService;

    Instruction instruction = new Instruction(1L, new Date(), null, true, "Description",
            1, 1.0F);

    Pageable pageable;

    @BeforeEach
    void setup(){
        instructionRepository = Mockito.mock(InstructionRepository.class);
        instructionService = new InstructionService(instructionRepository);
        instruction.setId(1L);
        pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("nome"), Sort.Order.desc("id")));
    }

    @Test
    void save() {
        Mockito.when(instructionRepository.save(Mockito.any())).thenReturn(instruction);
        Assertions.assertNotNull(instructionService.save(instruction));
    }

    @Test
    void update() {
        Mockito.when(instructionRepository.existsById(Mockito.any())).thenReturn(false);
        Mockito.when(instructionRepository.save(Mockito.any())).thenReturn(instruction);
        Assertions.assertThrows(NotFoundException.class, () -> instructionService.update(instruction.getId(), instruction));

        Mockito.when(instructionRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.when(instructionRepository.save(Mockito.any())).thenReturn(instruction);
        Assertions.assertDoesNotThrow(() -> instructionService.update(instruction.getId(), instruction));
        Assertions.assertNotNull(instructionService.update(instruction.getId(), instruction));
    }

    @Test
    void delete() {
        Mockito.when(instructionRepository.findById(Mockito.any())).thenReturn(Optional.of(instruction));
        Mockito.when(instructionRepository.save(Mockito.any())).thenReturn(instruction);
        Assertions.assertNotNull(instructionService.delete(instruction.getId()));

        Mockito.when(instructionRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(instructionRepository.save(Mockito.any())).thenReturn(null);
        Assertions.assertNull(instructionService.delete(instruction.getId()));
    }

    @Test
    void search() {
        Mockito.when(instructionRepository.findAll(Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(instruction)));
        Mockito.when(instructionRepository.findAll(Mockito.any(BooleanExpression.class), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(instruction)));
        Mockito.when(instructionRepository.findAll()).thenReturn(Collections.singletonList(instruction));
        Assertions.assertNotNull(instructionService.search(pageable, "START id:1", true));
        Assertions.assertNotNull(instructionService.search(pageable, "START id:1", false));
        Assertions.assertNotNull(instructionService.search(pageable, "", true));
        Assertions.assertNotNull(instructionService.search(pageable, "", false));
    }

    @Test
    void loadById() {
        Mockito.when(instructionRepository.findById(Mockito.any())).thenReturn(Optional.of(instruction));
        Assertions.assertNotNull(instructionService.loadById(instruction.getId()));

        Mockito.when(instructionRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> instructionService.loadById(instruction.getId()));
    }

    @Test
    void getBasicRepository() {
        Assertions.assertNotNull(instructionService.getBasicRepository());
    }

    @Test
    void getSearchPredicate() {
        Assertions.assertNotNull(instructionService.getSearchPredicate(""));
    }
}
package com.topi.controller;

import com.topi.model.Instruction;
import com.topi.service.BasicService;
import com.topi.service.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class created to be the Controller of Instruction.
 *
 * @since 2021-03-14
 */

@RestController
@RequestMapping("/instructions")
public class InstructionController extends BasicController<Instruction> {

    /**
     * Instruction Service Instance.
     */
    private InstructionService instructionService;

    /**
     * Constructor with params.
     *
     * @param instructionService Instruction Service Instance.
     */
    @Autowired
    public InstructionController(InstructionService instructionService) {
        this.instructionService = instructionService;
    }

    /**
     * Method that returns to BasicController the specified Service of this class.
     *
     * @return {@link BasicService<Instruction>}. Specified Service of this class.
     */
    @Override
    protected BasicService<Instruction> getBasicService() {
        return instructionService;
    }
}

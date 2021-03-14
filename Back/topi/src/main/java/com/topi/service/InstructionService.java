package com.topi.service;

import com.topi.model.Instruction;
import com.topi.predicate.criteria.SearchPredicate;
import com.topi.predicate.impl.InstructionPredicate;
import com.topi.repository.BasicRepository;
import com.topi.repository.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class created to be the Service of Instruction.
 *
 * @since 2021-03-14
 */

@Service
public class InstructionService extends BasicService<Instruction> {

    /**
     * Instruction Repository Instance.
     */
    private InstructionRepository instructionRepository;

    /**
     * Constructor with params.
     *
     * @param instructionRepository Instruction Repository Instance.
     */
    @Autowired
    public InstructionService(InstructionRepository instructionRepository) {
        this.instructionRepository = instructionRepository;
    }

    /**
     * Method that return to BasicService the Repository specified of this class.
     *
     * @return {@link BasicRepository<Instruction>}. Repository specified on the runtime.
     */
    @Override
    protected BasicRepository<Instruction> getBasicRepository() {
        return instructionRepository;
    }

    /**
     * Method that returns to BasicService the Predicate specified of this class.
     *
     * @param search Criteria that's being searched.
     * @return {@link SearchPredicate<Instruction>}. SearchPredicate specified on the runtime.
     */
    @Override
    protected SearchPredicate<Instruction> getSearchPredicate(String search) {
        return new InstructionPredicate(search);
    }
}

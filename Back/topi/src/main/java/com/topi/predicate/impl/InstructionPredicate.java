package com.topi.predicate.impl;

import com.topi.model.Instruction;
import com.topi.predicate.criteria.SearchPredicate;

/**
 * Class created to implement the Search Predicate for Instruction.
 *
 * @since 2021-03-14
 */

public class InstructionPredicate extends SearchPredicate<Instruction> {

    /**
     * Constructor with params.
     *
     * @param search String Search that contains the criteira that is being searched.
     */
    public InstructionPredicate(String search) {
        super(Instruction.class, search);
    }
}

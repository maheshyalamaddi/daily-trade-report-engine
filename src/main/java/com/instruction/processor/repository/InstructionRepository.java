package com.instruction.processor.repository;

import java.util.List;

import com.instruction.processor.dto.Instruction;

/**
 * An Interface holding definition of repository contracts will be used in while dealing with trading instruction data.
 * 
 * @author 
 *
 */
public interface InstructionRepository {

	void persist(Instruction instruction);

	List<Instruction> loadAll();
}

/**
 * 
 */
package com.instruction.processor.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.instruction.processor.dto.Instruction;

/**
 * An in -memory repository implementation to hold the instructions data.
 * 
 * @author
 *
 */
@Repository
public class InstructionRepositoryImpl implements InstructionRepository {

	@Override
	public void persist(Instruction instruction) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Instruction> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

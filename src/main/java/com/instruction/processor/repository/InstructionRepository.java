package com.instruction.processor.repository;

import java.util.List;

import com.instruction.processor.dto.Instruction;

public interface InstructionRepository {

	void persist(Instruction instruction);

	List<Instruction> loadAll();
}

package com.instruction.processor.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.instruction.processor.dto.Instruction;
import com.instruction.processor.repository.InstructionRepositoryImpl;

@RunWith(MockitoJUnitRunner.class)
public class InstructionProcessorTest {

	@InjectMocks
	InstructionProcessor instructionProcessor = new InstructionProcessor();

	@Mock
	InstructionRepositoryImpl instructionRepositoryImpl;

	@Before
	public void setUp() throws Exception {
		Instruction instruction = new Instruction();
		instruction.setFinancialEntity("E1");
		instruction.setBuyRSellIndicator("S");
		instruction.setAgreedFix(0.50);
		instruction.setCurrency("AED");
		instruction.setInstructionDate(new Date());
		instruction.setSettlementDate(new Date());
		instruction.setUnits(1);
		instruction.setUnitPrice(110.25);

		List<Instruction> instructionList = new ArrayList<Instruction>();
		instructionList.add(instruction);

		when(instructionRepositoryImpl.loadAll()).thenReturn(instructionList);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecute() {
		instructionProcessor.execute();
	}

}

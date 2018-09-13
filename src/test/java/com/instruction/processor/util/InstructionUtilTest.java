package com.instruction.processor.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.instruction.processor.dto.Instruction;

@RunWith(MockitoJUnitRunner.class)
public class InstructionUtilTest {

	protected Instruction instructionMock = new Instruction();

	@Before
	public void setUp() throws Exception {
		instructionMock.setFinancialEntity("TestONE");
	}

	@After
	public void tearDown() throws Exception {
		instructionMock = null;
	}

	@Test
	public void testCreateInstruction() {
		assertNotNull(InstructionUtil.createInstruction("", "", 0, "", new Date(), new Date(), 0, 0));
		assertEquals("mocked data not matching", instructionMock.getFinancialEntity(),
				InstructionUtil.createInstruction("TestONE", "", 0, "", new Date(), new Date(), 0, 0).getFinancialEntity());
	}

	@Test
	public void testIsDayOfProcessingWeek() {
		assertNotNull("given AED day processing failed", InstructionUtil.isDayOfProcessingWeek(new Date(), "AED"));
		assertNotNull("given SAR day processing failed", InstructionUtil.isDayOfProcessingWeek(new Date(), "SAR"));
		assertNotNull("given SGP N Other day processing failed", InstructionUtil.isDayOfProcessingWeek(new Date(), "SGP"));
	}

	@Test
	public void testGetDayOfWeek() {
		assertNotNull("given date failed to get day of week", InstructionUtil.getDayOfWeek(new Date()));
	}

	@Test
	public void testGetNextDateOfWeek() {
		assertNotNull("given date failed to get next day of week", InstructionUtil.getNextDateOfWeek(new Date()));
	}

	@Test
	public void testGetDateInddMMYYFormat() {
		assertNotNull("given date failed to format in dd MMM yyyy", InstructionUtil.getDateInddMMYYFormat(new Date()));
	}

	@Test
	public void testGetDateFromString() {
		assertNotNull("given date failed to parse in dd MMM yyyy", InstructionUtil.getDateFromString("13 Sep 2018"));
	}

}

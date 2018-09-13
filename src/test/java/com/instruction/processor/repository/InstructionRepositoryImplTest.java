package com.instruction.processor.repository;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.instruction.processor.dto.Instruction;

@RunWith(MockitoJUnitRunner.class)
public class InstructionRepositoryImplTest {

	@InjectMocks
	InstructionRepositoryImpl InstructionRepositoryImpl;

	@Mock
	JdbcTemplate jdbctemplate;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		assertNotNull("jdbctemplate is empty", jdbctemplate);
		// doReturn(0).when(jdbctemplate.update(anyString(), any(String.class)));
		List<Instruction> instructionList = new ArrayList<Instruction>();
		when(jdbctemplate.query(anyString(), any(RowMapper.class))).thenReturn(instructionList);
	}

	@Test
	public void testPersist() {
		InstructionRepositoryImpl.persist(new Instruction());
	}

	@Test
	public void testLoadAll() {
		assertNotNull("returned resultset list is null", InstructionRepositoryImpl.loadAll());
	}

}

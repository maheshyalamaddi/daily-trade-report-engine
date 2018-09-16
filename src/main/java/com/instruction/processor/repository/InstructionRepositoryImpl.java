/**
 * 
 */
package com.instruction.processor.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.instruction.processor.dto.Instruction;

/**
 * An in -memory repository implementation to save the trading instructions data into database.
 * And retrieving all the persisted data from database to collection of DTO Objects
 * 
 * @author
 *
 */
@Repository
public class InstructionRepositoryImpl implements InstructionRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Will save i.e. persist the given input trading instruction to in memory database.
	 * 
	 */
	@Override
	public void persist(Instruction instruction) {
		String sql = "INSERT INTO INSTRUCTION (ENTITY, BUY_R_SELL, AGREEDFIX, CURRENCY, INSTUCTION_DATE, SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT) values (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, instruction.getFinancialEntity(), instruction.getBuyRSellIndicator(), instruction.getAgreedFix(),
				instruction.getCurrency(), instruction.getInstructionDate(), instruction.getSettlementDate(), instruction.getUnits(),
				instruction.getUnitPrice());
	}

	/**
	 * Will be loading all the mocked OR available trading data from database.
	 * 
	 * @return
	 */
	@Override
	public List<Instruction> loadAll() {
		return jdbcTemplate.query("SELECT * FROM INSTRUCTION", (resultSet, i) -> {
			return toInstruction(resultSet);
		});
	}

	/**
	 * Mapping database result set to Instruction DTO.
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private Instruction toInstruction(ResultSet resultSet) throws SQLException {
		Instruction instruction = new Instruction();
		instruction.setId(resultSet.getLong("ID"));
		instruction.setFinancialEntity(resultSet.getString("ENTITY"));
		instruction.setBuyRSellIndicator(resultSet.getString("BUY_R_SELL"));
		instruction.setAgreedFix(resultSet.getDouble("AGREEDFIX"));
		instruction.setCurrency(resultSet.getString("CURRENCY"));
		instruction.setInstructionDate(resultSet.getDate("INSTUCTION_DATE"));
		instruction.setSettlementDate(resultSet.getDate("SETTLEMENT_DATE"));
		instruction.setUnits(resultSet.getInt("UNITS"));
		instruction.setUnitPrice(resultSet.getDouble("PRICE_PER_UNIT"));
		return instruction;
	}

}

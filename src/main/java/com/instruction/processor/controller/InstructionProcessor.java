package com.instruction.processor.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.instruction.processor.dto.Instruction;
import com.instruction.processor.dto.InstructionReport;
import com.instruction.processor.repository.InstructionRepositoryImpl;
import com.instruction.processor.util.InstructionUtil;

/**
 * A processor which will prepare the mock data for instruction and process the same to generate the financial entity
 * wise in-bound and out-bound trading details.
 * 
 * @author
 *
 */
@Controller
public class InstructionProcessor {
	private static final Logger logger = LoggerFactory.getLogger(InstructionProcessor.class);

	@Autowired
	InstructionRepositoryImpl instructionRepositoryImpl;

	List<Instruction> instructionList = null;
	Map<String, InstructionReport> instructionAfterProcessing = new HashMap<String, InstructionReport>();

	/**
	 * 
	 */
	public void execute() {
		prepareInstruction();
		if (!StringUtils.isEmpty(instructionList)) {
			processInstruction();
		} else {
			logger.info("*********THERE IS NO MOCK DATA TO PROCESS HENCE SKIPPING THE REMAINING PROCESS LOGIC********\n");
		}
		generateInstructionReport();
	}

	private void prepareInstruction() {
		logger.info("*********PREPARING MOCK INSTRUCTION DATA*********\n");
		// ----------- valid records for Currency AED and SAR-------------
		Instruction instruction = InstructionUtil.createInstruction("Valid SAR", "B", 0.50, "SAR", "09 Sep 2018",
				InstructionUtil.getDateInddMMYYFormat(new Date()), 200, 100.25);
		instructionRepositoryImpl.persist(instruction);
		instruction = InstructionUtil.createInstruction("Valid AED", "S", 0.22, "AED", "13 Sep 2018",
				InstructionUtil.getDateInddMMYYFormat(new Date()), 450, 150.50);
		instructionRepositoryImpl.persist(instruction);

		// ----------- valid records for Currency SGP and OTHER-------------
		instruction = InstructionUtil.createInstruction("Valid SGP", "B", 0.50, "SGP", "07 Sep 2018",
				InstructionUtil.getDateInddMMYYFormat(new Date()), 200, 100.25);
		instructionRepositoryImpl.persist(instruction);
		instruction = InstructionUtil.createInstruction("Valid OTHER", "S", 0.22, "OTHER", "10 Sep 2018",
				InstructionUtil.getDateInddMMYYFormat(new Date()), 450, 150.50);
		instructionRepositoryImpl.persist(instruction);

		// ----------- Invalid records SatuarDay and Sunday are Holidays for
		// Currency AED and SAR-------------
		instruction = InstructionUtil.createInstruction("bar", "S", 0.22, "SAR", "08 Sep 2018",
				InstructionUtil.getDateInddMMYYFormat(new Date()), 450, 150.50);
		// instructionRepositoryImpl.persist(instruction); instruction =
		InstructionUtil.createInstruction("foo1", "B", 0.50, "AED", "08 Sep 2018", InstructionUtil.getDateInddMMYYFormat(new Date()), 200,
				100.25);
		// instructionRepositoryImpl.persist(instruction);

		// ----------- Invalid records SatuarDay and Sunday are Holidays for
		// Currency OTHER-------------
		instruction = InstructionUtil.createInstruction("bar1", "S", 0.22, "OTHER", "08 Sep 2018",
				InstructionUtil.getDateInddMMYYFormat(new Date()), 450, 150.50);
		// instructionRepositoryImpl.persist(instruction); instruction =
		InstructionUtil.createInstruction("foo2", "S", 0.50, "SAR", "09 Sep 2018", InstructionUtil.getDateInddMMYYFormat(new Date()), 200,
				100.25);
		// instructionRepositoryImpl.persist(instruction);

		// ----------- Invalid records settlement Date falls on Sunday week end will
		// be moved to next working day-------------
		instruction = InstructionUtil.createInstruction("SettlementDate Will be Moved NextWorking Day ", "S", 0.22, "OTHER",
				InstructionUtil.getDateInddMMYYFormat(new Date()), "09 Sep 2018", 450, 150.50);
		instruction = InstructionUtil.createInstruction("SettlementDate Will be Moved NextWorking Day ", "B", 0.22, "AED",
				InstructionUtil.getDateInddMMYYFormat(new Date()), "09 Sep 2018", 450, 150.50);
		instruction = InstructionUtil.createInstruction("SettlementDate Will be Moved NextWorking Day ", "S", 0.22, "SAR",
				InstructionUtil.getDateInddMMYYFormat(new Date()), "09 Sep 2018", 450, 150.50);
		// instructionRepositoryImpl.persist(instruction);
		// ----------- Invalid records -------------

		logger.info("retrieving all the loaded sample data from in memory: ");
		// instructionList = instructionRepositoryImpl.loadAll();
//		for (Instruction instructionItem : instructionList) {
//			logger.info("" + instructionItem);
//		}
	}

	private void processInstruction() {
		logger.info("-------------PROCESSING INSTRUCTION DATA--------------------------------\n");
		int i = 0;
		for (Instruction instructionItem : instructionList) {
			String key = null;
			if (InstructionUtil.isDayOfProcessingWeek(instructionItem.getInstructionDate(), instructionItem.getCurrency())) {
				if (InstructionUtil.getDayOfWeek(instructionItem.getSettlementDate()) == Calendar.SUNDAY) {
					logger.info(
							"settlement date " + instructionItem.getSettlementDate() + "is on week end so changing it to next working day");
					instructionItem.setSettlementDate(InstructionUtil.getNextDateOfWeek(instructionItem.getSettlementDate()));
				}
				key = instructionItem.getBuyRSellIndicator() + instructionItem.getFinancialEntity() + instructionItem.getSettlementDate();
				InstructionUtil.generateInstructionReport(instructionItem, key, i++, instructionAfterProcessing);
			} else {
				logger.info("ignoring and not processing the instruction " + instructionItem + " \n as the day of week "
						+ InstructionUtil.getDayOfWeek(instructionItem.getInstructionDate()) + " not mathes as per currency: "
						+ instructionItem.getCurrency());
			}
		}

		logger.info("---------------------------------------------\n");
	}

	private void generateInstructionReport() {
		logger.info("==REPORTING INSTRUCTION DATA AFTER PROCESSING==\n");
		Map<String, InstructionReport> sortedNewMap = instructionAfterProcessing.entrySet().stream()
				.sorted((instructionReport1, instructionReport2) -> Double.compare(instructionReport1.getValue().getOutgoingAmountInUSD(),
						instructionReport2.getValue().getOutgoingAmountInUSD()))
				.sorted((instructionReport1, instructionReport2) -> Double.compare(instructionReport1.getValue().getIncomingAmountInUSD(),
						instructionReport2.getValue().getIncomingAmountInUSD()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		sortedNewMap.forEach((key, val) -> {
			logger.info("the Generated report with required format " + (val.toString()));
		});
	}
}

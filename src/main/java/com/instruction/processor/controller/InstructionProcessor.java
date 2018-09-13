package com.instruction.processor.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;

import com.instruction.processor.dto.Instruction;
import com.instruction.processor.dto.InstructionReport;
import com.instruction.processor.repository.InstructionRepositoryImpl;
import com.instruction.processor.util.InstructionConstants;
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
	List<InstructionReport> instructionAfterProcessing = new ArrayList<InstructionReport>();

	public void execute() {
		prepareInstruction();
		processInstruction();
		generateInstructionReport();
	}

	private void prepareInstruction() {
		instructionList = instructionRepositoryImpl.loadAll();
		logger.info("loaing predefined mock data from in memory H2 --> \n\t{}", instructionList);
	}

	private void processInstruction() {
		if (!ObjectUtils.isEmpty(instructionList)) {
			int counter = 0;
			for (Instruction instructionItem : instructionList) {
				logger.info("processinginstruction record -->{} :: {}", counter, instructionItem);
				if (InstructionUtil.isDayOfProcessingWeek(instructionItem.getInstructionDate(), instructionItem.getCurrency())) {
					if (InstructionUtil.getDayOfWeek(instructionItem.getSettlementDate()) == Calendar.SUNDAY) {
						logger.info("settlement date {} is on week end so changing it to next working day.",
								instructionItem.getSettlementDate());
						instructionItem.setSettlementDate(InstructionUtil.getNextDateOfWeek(instructionItem.getSettlementDate()));
						logger.info("settlement date {} after change ::", instructionItem.getSettlementDate());
					}
					prepareInstructionReport(instructionItem, counter);
				} else {
					logger.info("ignoring and not processing the instruction -->" + instructionItem + " \n\t as the day of week "
							+ InstructionUtil.getDayOfWeek(instructionItem.getInstructionDate()) + " not mathes as per currency: "
							+ instructionItem.getCurrency());
				}
				counter++;
			}
		} else {
			logger.info("*********THERE IS NO MOCK DATA TO PROCESS HENCE SKIPPING THE REMAINING PROCESS LOGIC********\n");
		}
	}

	private void generateInstructionReport() {
		if (!ObjectUtils.isEmpty(instructionAfterProcessing)) {
			 Map<String, Map<String, List<InstructionReport>>> outComeGroupedMap=  instructionAfterProcessing.stream()
					 .collect(Collectors.groupingBy(InstructionReport::getFinancialEntity,Collectors.groupingBy(InstructionReport::getBuyRSellIndicator)));
			 logger.info("reporting instruction data after processing ::");
			 for(Map.Entry<String, Map<String,List<InstructionReport>>> groupItem : outComeGroupedMap.entrySet()) {
				 logger.info("record grouping by entity :--> {}", groupItem.getKey());
				 if(!ObjectUtils.isEmpty(groupItem)) {
					 for(Map.Entry<String,List<InstructionReport>> groupInnerItem : (groupItem.getValue()).entrySet()) {
						 logger.info(" and then sell/buy & it's details : --> {}", groupInnerItem.toString());
					 }
				 }
			 }
		}
	}
	
	private void prepareInstructionReport(Instruction instructionItem, int recordCount) {
		InstructionReport instructionReport = new InstructionReport();
		instructionReport.setFinancialEntity(instructionItem.getFinancialEntity());
		instructionReport.setBuyRSellIndicator(instructionItem.getBuyRSellIndicator());
		instructionReport.setInstructionDate(instructionItem.getSettlementDate());
		instructionReport.setSettlementDate(instructionItem.getSettlementDate());
		instructionReport.setCurrency(instructionItem.getCurrency());
		double totalTradingAmount = instructionItem.getAgreedFix() * instructionItem.getUnitPrice() * instructionItem.getUnits();
		if (instructionItem.getBuyRSellIndicator().equalsIgnoreCase(InstructionConstants.INCOMING_TRADING)) {
			instructionReport.setIncomingAmountInUSD(totalTradingAmount);
		} else if (instructionItem.getBuyRSellIndicator().equalsIgnoreCase(InstructionConstants.OUTGOING_TRADING)) {
			instructionReport.setOutgoingAmountInUSD(totalTradingAmount);
		}
		instructionReport.setId(recordCount);
		instructionAfterProcessing.add(recordCount, instructionReport);
	}
}

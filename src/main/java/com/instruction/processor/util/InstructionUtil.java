package com.instruction.processor.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.instruction.processor.dto.Instruction;
import com.instruction.processor.dto.InstructionReport;

public class InstructionUtil {

	private static final Logger logger = LoggerFactory.getLogger(InstructionUtil.class);
	private static final DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

	public static Instruction createInstruction(String financialEntity, String buyRSellIndicator, double agrredFix, String currency,
			String instructionDate, String settlementDate, long units, double unitPrice) {
		Instruction instruction = new Instruction();
		instruction.setFinancialEntity(financialEntity);
		instruction.setBuyRSellIndicator(buyRSellIndicator);
		instruction.setAgreedFix(agrredFix);
		instruction.setCurrency(currency);
		instruction.setInstructionDate(instructionDate);
		instruction.setSettlementDate(settlementDate);
		instruction.setUnits(units);
		instruction.setUnitPrice(unitPrice);
		return instruction;
	}

	public static boolean isDayOfProcessingWeek(String givenDate, String currency) {
		if (InstructionConstants.CURRENCY_AED.equalsIgnoreCase(currency) || InstructionConstants.CURRENCY_SAR.equalsIgnoreCase(currency)) {
			return InstructionConstants.DAY_OF_WORK_WEEK_FOR_AED_N_SAR.containsKey(getDayOfWeek(givenDate));
		} else {
			return InstructionConstants.DAY_OF_WORK_WEEK_FOR_OTHERS.containsKey(getDayOfWeek(givenDate));
		}
	}

	public static int getDayOfWeek(String givenDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(getDateFromString(givenDate));
		return c.get(Calendar.DAY_OF_WEEK);
	}

	public static String getNextDateOfWeek(String givenDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(getDateFromString(givenDate));
		c.add(Calendar.DAY_OF_YEAR, 1);
		return getDateInddMMYYFormat(c.getTime());
	}

	public static void generateInstructionReport(Instruction instructionItem, String itemKey, int recordCount,
			Map<String, InstructionReport> instructionAfterProcessing) {
		InstructionReport instructionReport = new InstructionReport();
		double totalIncomingTradingAmount = 0.0;
		double totalOutgoingTradingAmount = 0.0;

		if (null != instructionAfterProcessing.get(itemKey)) {
			instructionReport = instructionAfterProcessing.get(itemKey);
			totalIncomingTradingAmount = instructionReport.getIncomingAmountInUSD();
			totalOutgoingTradingAmount = instructionReport.getOutgoingAmountInUSD();
		}
		instructionReport.setEntityRankByTradingAmount(recordCount);
		double totalTradingAmount = instructionItem.getAgreedFix() * instructionItem.getUnitPrice() * instructionItem.getUnits();
		if (instructionItem.getBuyRSellIndicator().equalsIgnoreCase(InstructionConstants.INCOMING_TRADING)) {
			instructionReport.setIncomingAmountInUSD(totalTradingAmount + totalIncomingTradingAmount);
		} else if (instructionItem.getBuyRSellIndicator().equalsIgnoreCase(InstructionConstants.OUTGOING_TRADING)) {
			instructionReport.setOutgoingAmountInUSD(totalTradingAmount + totalOutgoingTradingAmount);
		}
		instructionAfterProcessing.put(itemKey, instructionReport);
	}

	public static String getDateInddMMYYFormat(Date givenDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(givenDate);
		logger.info("for given input entity instruction/settlementDate date" + givenDate);
		return formatter.format(c.getTime());
	}

	public static Date getDateFromString(String givenDate) {
		Date dateTobeReturn = null;
		try {
			dateTobeReturn = formatter.parse(givenDate);
		} catch (ParseException e) {
			logger.info("for given input entity instruction/settlementDate string date - date conversion failed " + givenDate);
		}
		return dateTobeReturn;
	}
}

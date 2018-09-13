package com.instruction.processor.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.instruction.processor.dto.Instruction;

public class InstructionUtil {

	private static final Logger logger = LoggerFactory.getLogger(InstructionUtil.class);
	private static final DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

	public static Instruction createInstruction(String financialEntity, String buyRSellIndicator, double agrredFix, String currency,
			Date instructionDate, Date settlementDate, int units, double unitPrice) {
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

	public static boolean isDayOfProcessingWeek(Date processingDate, String currency) {
		if (InstructionConstants.CURRENCY_AED.equalsIgnoreCase(currency) || InstructionConstants.CURRENCY_SAR.equalsIgnoreCase(currency)) {
			return InstructionConstants.DAY_OF_WORK_WEEK_FOR_AED_N_SAR.containsKey(getDayOfWeek(processingDate));
		} else {
			return InstructionConstants.DAY_OF_WORK_WEEK_FOR_SGP_OTHERS.containsKey(getDayOfWeek(processingDate));
		}
	}

	public static int getDayOfWeek(Date givenDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(givenDate);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	public static Date getNextDateOfWeek(Date settlementDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(settlementDate);
		c.add(Calendar.DAY_OF_YEAR, 1);
		return c.getTime();
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

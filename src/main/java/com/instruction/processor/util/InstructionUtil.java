package com.instruction.processor.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.instruction.processor.bean.Instruction;

/**
 * An utility class which has helper methods used in daily-trade instruction processing report.
 * 
 * @author 
 *
 */
public class InstructionUtil {

	private static final Logger logger = LoggerFactory.getLogger(InstructionUtil.class);
	private static final DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

	/**
	 * Will give Instruction data transfer object based on the given input properties.
	 * 
	 * @param financialEntity
	 * @param buyRSellIndicator
	 * @param agrredFix
	 * @param currency
	 * @param instructionDate
	 * @param settlementDate
	 * @param units
	 * @param unitPrice
	 * @return
	 */
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

	/**
	 * Will identify the working day of the particular trading instruction by taking processingDate and Currency of the instruction,
	 * To decide whether it should be processed OR considered for the report generation.
	 * 
	 * @param processingDate
	 * @param currency
	 * @return
	 */
	public static boolean isDayOfProcessingWeek(Date processingDate, String currency) {
		if (InstructionConstants.CURRENCY_AED.equalsIgnoreCase(currency) || InstructionConstants.CURRENCY_SAR.equalsIgnoreCase(currency)) {
			return InstructionConstants.DAY_OF_WORK_WEEK_FOR_AED_N_SAR.containsKey(getDayOfWeek(processingDate));
		} else {
			return InstructionConstants.DAY_OF_WORK_WEEK_FOR_SGP_OTHERS.containsKey(getDayOfWeek(processingDate));
		}
	}

	/**
	 * Will give Calendar Day of the Week for the given input Java Date.
	 * 
	 * @param givenDate
	 * @return
	 */
	public static int getDayOfWeek(Date givenDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(givenDate);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Will give the Calendar Next Date of the week for the given input Java Date.
	 * 
	 * @param settlementDate
	 * @return
	 */
	public static Date getNextDateOfWeek(Date settlementDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(settlementDate);
		c.add(Calendar.DAY_OF_YEAR, 1);
		return c.getTime();
	}

	/**
	 * Will be giving parsed dd MM YYYY date formated String for the given Java Date.
	 * 
	 * @param givenDate
	 * @return
	 */
	public static String getDateInddMMYYFormat(Date givenDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(givenDate);
		logger.info("for given input entity instruction/settlementDate date" + givenDate);
		return formatter.format(c.getTime());
	}

	/**
	 * Given String dd MM YYYY Date Object will be converted to Java Date.
	 * 
	 * @param givenDate
	 * @return
	 */
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

package com.instruction.processor.util;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Constants which are applicable for daily-trade instruction processing report.
 * 
 * @author 
 *
 */
public interface InstructionConstants {

	public static final String CURRENCY_AED = "AED";
	public static final String CURRENCY_SAR = "SAR";
	public static final String CURRENCY_SGP_OTHER = "SGP";
	public static final String INCOMING_TRADING = "B";
	public static final String OUTGOING_TRADING = "S";

	public static final Map<Integer, String> DAY_OF_WORK_WEEK_FOR_SGP_OTHERS = initSgpOthersMap();

	static Map<Integer, String> initSgpOthersMap() {
		Map<Integer, String> mapObject = new HashMap<>();
		mapObject.put(Calendar.MONDAY, "MONDAY");
		mapObject.put(Calendar.TUESDAY, "TUESDAY");
		mapObject.put(Calendar.WEDNESDAY, "WEDNESDAY");
		mapObject.put(Calendar.THURSDAY, "THURSDAY");
		mapObject.put(Calendar.FRIDAY, "FRIDAY");
		return Collections.unmodifiableMap(mapObject);
	}

	public static final Map<Integer, String> DAY_OF_WORK_WEEK_FOR_AED_N_SAR = initAedNSarMap();

	static Map<Integer, String> initAedNSarMap() {
		Map<Integer, String> mapObject = new HashMap<>();
		mapObject.put(Calendar.SUNDAY, "SUNDAY");
		mapObject.put(Calendar.MONDAY, "MONDAY");
		mapObject.put(Calendar.TUESDAY, "TUESDAY");
		mapObject.put(Calendar.WEDNESDAY, "WEDNESDAY");
		mapObject.put(Calendar.THURSDAY, "THURSDAY");
		return Collections.unmodifiableMap(mapObject);
	}
}

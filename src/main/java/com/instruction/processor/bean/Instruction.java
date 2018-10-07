package com.instruction.processor.bean;

import java.util.Date;

import lombok.Data;

/**
 * Trading instruction from financial entity which holds both buy and sell
 * 
 * @author
 *
 */
@Data
public class Instruction {
	private long id;
	private String financialEntity;
	private String buyRSellIndicator;
	private double agreedFix;
	private String currency;
	private Date instructionDate;
	private Date settlementDate;
	private int units;
	private double unitPrice;
}

package com.instruction.processor.dto;

import java.util.Date;

/**
 * Trading instruction from financial entity which holds both buy and sell
 * 
 * @author
 *
 */
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFinancialEntity() {
		return financialEntity;
	}

	public void setFinancialEntity(String financialEntity) {
		this.financialEntity = financialEntity;
	}

	public String getBuyRSellIndicator() {
		return buyRSellIndicator;
	}

	public void setBuyRSellIndicator(String buyRSellIndicator) {
		this.buyRSellIndicator = buyRSellIndicator;
	}

	public double getAgreedFix() {
		return agreedFix;
	}

	public void setAgreedFix(double agreedFix) {
		this.agreedFix = agreedFix;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return String.format(
				"Instruction [id=%s, financialEntity=%s, buyRSellIndicator=%s, agreedFix=%s, currency=%s, instructionDate=%s, settlementDate=%s, units=%s, unitPrice=%s]",
				id, financialEntity, buyRSellIndicator, agreedFix, currency, instructionDate, settlementDate, units, unitPrice);
	}
}

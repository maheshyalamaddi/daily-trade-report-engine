package com.instruction.processor.dto;

/**
 * Inbound instruction from financial entity which holds both buy and sell
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
	private String instructionDate;
	private String settlementDate;
	private long units;
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

	public String getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(String instructionDate) {
		this.instructionDate = instructionDate;
	}

	public String getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}

	public long getUnits() {
		return units;
	}

	public void setUnits(long units) {
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
		StringBuilder builder = new StringBuilder();
		builder.append("Instructions [id=").append(id).append(", ");
		if (financialEntity != null)
			builder.append("financialEntity=").append(financialEntity).append(", ");
		if (buyRSellIndicator != null)
			builder.append("buyRSellIndicator=").append(buyRSellIndicator).append(", ");
		builder.append("agreedFix=").append(agreedFix).append(", ");
		if (currency != null)
			builder.append("currency=").append(currency).append(", ");
		if (instructionDate != null)
			builder.append("instructionDate=").append(instructionDate).append(", ");
		if (settlementDate != null)
			builder.append("settlementDate=").append(settlementDate).append(", ");
		builder.append("units=").append(units).append(", unitPrice=").append(unitPrice).append("]");
		return builder.toString();
	}
}

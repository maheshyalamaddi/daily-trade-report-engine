package com.instruction.processor.dto;

/**
 * Trading report record after Instruction processing.
 * 
 * @author
 *
 */
public class InstructionReport extends Instruction {
	@Override
	public String toString() {
		return String.format(
				"InstructionReport [incomingAmountInUSD=%s, outgoingAmountInUSD=%s, getFinancialEntity()=%s, getBuyRSellIndicator()=%s, getAgreedFix()=%s, getCurrency()=%s, getInstructionDate()=%s, getSettlementDate()=%s, getUnits()=%s, getUnitPrice()=%s]",
				incomingAmountInUSD, outgoingAmountInUSD, getFinancialEntity(), getBuyRSellIndicator(), getAgreedFix(), getCurrency(),
				getInstructionDate(), getSettlementDate(), getUnits(), getUnitPrice());
	}

	private double incomingAmountInUSD;
	private double outgoingAmountInUSD;

	public double getIncomingAmountInUSD() {
		return incomingAmountInUSD;
	}

	public void setIncomingAmountInUSD(double incomingAmountInUSD) {
		this.incomingAmountInUSD = incomingAmountInUSD;
	}

	public double getOutgoingAmountInUSD() {
		return outgoingAmountInUSD;
	}

	public void setOutgoingAmountInUSD(double outgoingAmountInUSD) {
		this.outgoingAmountInUSD = outgoingAmountInUSD;
	}
}

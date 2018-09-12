package com.instruction.processor.dto;

/**
 * Outbound report record after Instruction processing
 * 
 * @author
 *
 */
public class InstructionReport implements Comparable<InstructionReport> {
	private double incomingAmountInUSD;
	private double outgoingAmountInUSD;
	private long entityRankByTradingAmount;

	@Override
	public int compareTo(InstructionReport o) {
		return Long.compare(entityRankByTradingAmount, o.getEntityRankByTradingAmount());
	}

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

	public long getEntityRankByTradingAmount() {
		return entityRankByTradingAmount;
	}

	public void setEntityRankByTradingAmount(long entityRankByTradingAmount) {
		this.entityRankByTradingAmount = entityRankByTradingAmount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InstructionReport [incomingAmountInUSD=").append(incomingAmountInUSD).append(", outgoingAmountInUSD=")
				.append(outgoingAmountInUSD).append(", entityRankByTradingAmount=").append(entityRankByTradingAmount).append("]");
		return builder.toString();
	}

}

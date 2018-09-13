package com.instruction.processor.dto;

/**
 * Outbound report record after Instruction processing
 * 
 * @author
 *
 */
public class InstructionReport extends Instruction {
	@Override
	public String toString() {
		return String.format("InstructionReport [incomingAmountInUSD=%s, outgoingAmountInUSD=%s]", incomingAmountInUSD,
				outgoingAmountInUSD);
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

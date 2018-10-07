package com.instruction.processor.bean;

import lombok.Data;

/**
 * Trading report record after Instruction processing.
 * 
 * @author
 *
 */
@Data
public class InstructionReport extends Instruction {
	private double incomingAmountInUSD;
	private double outgoingAmountInUSD;
}

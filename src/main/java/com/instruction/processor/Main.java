package com.instruction.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.instruction.processor.controller.InstructionProcessor;

@SpringBootApplication
public class Main implements CommandLineRunner {

	@Autowired
	private InstructionProcessor instructionProcessor;

	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication(Main.class);
		sa.run(args);
	}

	@Override
	public void run(String... args) {
		instructionProcessor.execute();
	}
}

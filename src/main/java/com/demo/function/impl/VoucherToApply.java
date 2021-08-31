package com.demo.function.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VoucherToApply implements Function<String, String> {

	@Autowired
	private DmnEngine dmnEngine;

	@Autowired
	@Qualifier(value = "voucherDmn")
	private DmnDecision decision;

	// private static final Logger log =
	// LoggerFactory.getLogger(FunctionApplication.class);

	@Override
	public String apply(String i) {
		
		log.info("Deciding for a voucher with input {}", i);
		String[] tokens = i.split("\\|");
		//Arrays.stream(i.split("\\|")).forEach(t -> log.info(t));

		DmnDecisionTableResult result = dmnEngine.evaluateDecisionTable(decision, Variables
				.putValue("dayWeek", LocalDateTime.parse(tokens[0], DateTimeFormatter.ISO_DATE_TIME).getDayOfWeek())
				.putValue("isoId", tokens[1]).putValue("isoDescription", tokens[2]));

		return (String) result.getSingleResult().get("voucherKey");

	}

}

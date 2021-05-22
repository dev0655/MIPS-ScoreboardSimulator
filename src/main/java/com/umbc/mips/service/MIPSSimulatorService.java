package com.umbc.mips.service;

import org.springframework.stereotype.Component;

import com.umbc.mips.model.Response;

@Component
public interface MIPSSimulatorService {

	
	public Response executeSimulator(String instructions);
	
}

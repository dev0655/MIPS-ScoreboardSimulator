package com.umbc.mips.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface MIPSSimulatorService {

	
	public int[] executeSimulator(String instructions);
	
}

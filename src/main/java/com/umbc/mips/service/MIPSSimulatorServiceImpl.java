package com.umbc.mips.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MIPSSimulatorServiceImpl implements MIPSSimulatorService {

	

	@Override
	public int[] executeSimulator(String instructions) {

        Parse.parse(instructions);
        Execute.printAllRegisters();
        Execute.execute();
        Execute.printAllRegisters();
		return Execute.registers;
	}

}

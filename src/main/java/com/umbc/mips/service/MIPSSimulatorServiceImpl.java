package com.umbc.mips.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.umbc.mips.model.Response;

@Service
public class MIPSSimulatorServiceImpl implements MIPSSimulatorService {

	

	@Override
	public Response executeSimulator(String instructions) {
		Response resp = new Response();
		List<Integer[]> arr= null;
        Parse.parse(instructions);
        Execute.printAllRegisters();
        arr= Execute.execute();
        resp.setResult(arr);
       char []mem = new char[1024];
       mem = Memory.mem.clone();

       int[] num = new int[mem.length];
       for (int i = 0; i < mem.length; i++) {
    	    num[i] = (int)mem[i];
    	}
        resp.setMemory(num);
        System.out.println("data is" +Memory.mem[0]);
        Execute.registers = new Integer[33];
        Memory.mem = new char[512];
        
        
		return resp;
	}

}

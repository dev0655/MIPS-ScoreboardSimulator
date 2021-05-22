package com.umbc.mips.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umbc.mips.model.Response;
import com.umbc.mips.service.MIPSSimulatorService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MIPSRestController {

	@Autowired
	MIPSSimulatorService service;

	@GetMapping("/api/ping")
	public ResponseEntity<String> ping() throws IOException {
		return ResponseEntity.ok("Running");

	}

	@GetMapping("/api/executeSimulator")
	public ResponseEntity<Response> executeSimulator(@RequestParam("configFile") String instructions) throws IOException {
		
		Response result = null;
		if (null != instructions) {
			
			result = service.executeSimulator(instructions);
		} 
		
		return ResponseEntity.ok(result);

	}
}

package com.umbc.mips.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.umbc.mips.service.MIPSSimulatorService;

@RestController
public class MIPSRestController {

	@Autowired
	MIPSSimulatorService service;

	@GetMapping("/api/ping")
	public ResponseEntity<String> ping() throws IOException {
		return ResponseEntity.ok("Running");

	}

	@PostMapping("/api/executeSimulator")
	public ResponseEntity<String> executeSimulator(@RequestParam("configFile") String instructions) throws IOException {
		
		String result = null;
		if (null != instructions) {
			result = service.executeSimulator(instructions);
		} else {
			return ResponseEntity.ok("Add Instructions");
		}
		
		return ResponseEntity.ok(result);

	}
}

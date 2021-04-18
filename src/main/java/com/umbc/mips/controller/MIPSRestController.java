package com.umbc.mips.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/api/executeSimulator")
	public ResponseEntity<String> executeSimulator(@RequestParam("configFile") MultipartFile configFile,
			@RequestParam("instructionFile") MultipartFile instructionFile,
			@RequestParam("dataFile") MultipartFile dataFile) throws IOException {
		
		String result = null;
		if (null != configFile && null != instructionFile && null != dataFile) {
			result = service.executeSimulator(configFile, instructionFile, dataFile);
		} else {
			return ResponseEntity.ok("Upload All required Files");
		}
		
		return ResponseEntity.ok(result);

	}
}

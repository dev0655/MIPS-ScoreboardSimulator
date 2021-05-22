package com.umbc.mips.model;

import java.util.ArrayList;
import java.util.List;

public class Response {

	List<Integer[]> result= new ArrayList<Integer[]>();
	int[] memory = new int[512];
	String[] err = new String[5];
	
	public List<Integer[]> getResult() {
		return result;
	}
	public void setResult(List<Integer[]> result) {
		this.result = result;
	}
	public int[] getMemory() {
		return memory;
	}
	public void setMemory(int[] memory) {
		this.memory = memory;
	}
	public String[] getErr() {
		return err;
	}
	public void setErr(String[] err) {
		this.err = err;
	}
	
	

}

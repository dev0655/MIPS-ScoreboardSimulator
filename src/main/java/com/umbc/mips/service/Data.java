package com.umbc.mips.service; 
import java.util.*;

class Data {
    public static int InstructionWidth = 4; 

    // DataStructure to store all the instructions
    public static String [][] instructions;

    // DataStructure to store all the places, there are flags
    public static HashMap<String, Integer> flags;

    // Initialize all the data structures
    public static void Initialize(int length) {
        instructions = new String[length][InstructionWidth];
        flags = new HashMap<String, Integer>();
    }
}

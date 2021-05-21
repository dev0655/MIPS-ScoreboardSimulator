package com.umbc.mips.service;
import java.util.*;

class Parse {
	public static void parse(String input) {
        System.out.println("Inside the Parse class and parse method");
        String [] lines = input.split("\n");
        Data.Initialize(lines.length);

        for(int a = 0; a < lines.length; a++) {
            int i = 0;
            String [] words;

            // func: detect if the instruction specifies any flag
            if(lines[a].contains(":")) { 
                String []breakpoint = lines[a].split(":");
                breakpoint[0] = breakpoint[0].trim();
                breakpoint[1] = breakpoint[1].trim();
                Data.flags.put(breakpoint[0], a);

                words = breakpoint[1].split(" ");
            } else {
                words = lines[a].split(" ");
            }

            for(int b = 0; b < words.length; b++) {
                StringBuilder inst = new StringBuilder();

                // func: remove all commas 
                for(int c = 0; c < words[b].length() ; c++) {
                    if(words[b].charAt(c) != ',') inst.append(words[b].charAt(c));
                }

                Data.instructions[a][i++] = inst.toString();
            }
        }

//====================================PRINTING==================================== 
        System.out.println("Printing the instructions with separated fields");

        for(int a = 0; a < Data.instructions.length; a++) {
            for(int b = 0; b < Data.instructions[a].length; b++) {
                System.out.print(Data.instructions[a][b] + "\t");
            }
            System.out.println();
        }

        System.out.println("Flags detected at:");
        System.out.println(Data.flags);
//================================END PRINTING==================================== 
	}
}

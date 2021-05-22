package com.umbc.mips.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Execute {
    public static Integer [] registers = new Integer[33];

    public static List<Integer[]> execute() {
    	Arrays.fill(registers, 0);
    	List<Integer[]> data = new ArrayList<>();
        // Loop on all instructions
        for(int a = 0; a < Data.instructions.length; a++) {
            int dest = -1;
            switch(Data.instructions[a][0]) {
                case "ADD":
                    // ADD from registers
                    ADD(Data.instructions[a]);
                    break;
                case "ADDI":
                    // ADD Immediate to register
                    ADDI(Data.instructions[a]);
                    break;
                case "AND":
                    // Logical AND from registers
                    AND(Data.instructions[a]);
                    break;
                case "ANDI":
                    // Logical AND Immediate
                    ANDI(Data.instructions[a]);
                    break;
                case "B":
                    // Branch instruction
                    dest = B(Data.instructions[a]);
                    if(dest == -1) {
                        System.out.println("Flag not found in instructions");
                    } else {
                        a = dest;
                    }
                    break;
                case "BEQ":
                    // Branch if given registers are equal
                    dest = BEQ(Data.instructions[a]);
                    if(dest == -1) {
                        System.out.println("Flag not found in instructions");
                    } else {
                        a = dest;
                    }
                    break;
                case "BEQZ":
                    // Branch if given register is equal to zero
                    dest = BEQZ(Data.instructions[a]);
                    if(dest == -1) {
                        System.out.println("Flag not found in instructions");
                    } else {
                        a = dest;
                    }
                    break;
                case "BGEZ":
                    // Branch if given register is greater than or equal to zero
                    dest = BGEZ(Data.instructions[a]);
                    if(dest == -1) {
                        System.out.println("Flag not found in instructions");
                    } else {
                        a = dest;
                    }
                    break;
                case "BGTZ":
                    // Branch if given register is greater than 0
                    dest = BGTZ(Data.instructions[a]);
                    if(dest == -1) {
                        System.out.println("Flag not found in instructions");
                    } else {
                        a = dest;
                    }
                    break;
                case "BLEZ":
                    // Branch if given register is less than or equal to 0
                    dest = BLEZ(Data.instructions[a]);
                    if(dest == -1) {
                        System.out.println("Flag not found in instructions");
                    } else {
                        a = dest;
                    }
                    break;
                case "BLTZ":
                    // Branch if given register is less than 0
                    dest = BLTZ(Data.instructions[a]);
                    if(dest == -1) {
                        System.out.println("Flag not found in instructions");
                    } else {
                        a = dest;
                    }
                    break;
                case "BNE":
                    // Branch if given registers are not equal
                    dest = BNE(Data.instructions[a]);
                    if(dest == -1) {
                        System.out.println("Flag not found in instructions");
                    } else {
                        a = dest;
                    }
                    break;
                case "BNEZ":
                    // Branch if given register is not equal to zero
                    dest = BNEZ(Data.instructions[a]);
                    if(dest == -1) {
                        System.out.println("Flag not found in instructions");
                    } else {
                        a = dest;
                    }
                    break;
                case "DIV":
                    // DIV register by register
                    DIV(Data.instructions[a]);
                    break;
                case "LA":
                    // Load from address
                    LA(Data.instructions[a]);
                    break;
                case "LI":
                    // Load immediate
                    LI(Data.instructions[a]);
                    break;
                case "MOVE":
                    // copy from register to register
                    MOVE(Data.instructions[a]);
                    break;
                case "MOVN":
                    // Move if second source register is not equal to zero
                    MOVN(Data.instructions[a]);
                    break;
                case "MOVZ":
                    // Move if second source register is equal to zero
                    MOVZ(Data.instructions[a]);
                    break;
                case "MUL":
                    // Multiply registers
                    MUL(Data.instructions[a]);
                    break;
                case "NEG":
                    // Negate and copy from source to destination
                    NEG(Data.instructions[a]);
                    break;
                case "NOP":
                    // Do nothing
                    NOP();
                    break;
                case "NOR":
                    // Bitwise NOR
                    NOR(Data.instructions[a]);
                    break;
                case "NOT":
                    // Bitwise NOT
                    NOT(Data.instructions[a]);
                    break;
                case "OR":
                    // Bitwise OR
                    OR(Data.instructions[a]);
                    break;
                case "ORI":
                    // Bitwise OR with Immediate
                    ORI(Data.instructions[a]);
                    break;
                case "SD":
                    // Store from register into memory
                    SD(Data.instructions[a]);
                    break;
                case "SLT":
                    // Set destination register to 1 if reg1 < reg2
                    SLT(Data.instructions[a]);
                    break;
                case "SLTI":
                    // Set destination register to 1 if given src1 < src2
                    SLTI(Data.instructions[a]);
                    break;
                case "SUB":
                    // Arithmetic subtraction
                    SUB(Data.instructions[a]);
                    break;
                case "XOR":
                    // Bitwise XOR
                    XOR(Data.instructions[a]);
                    break;
                case "XORI":
                    // Bitwise XORI
                    XORI(Data.instructions[a]);
                    break;
                default:
                    System.out.println("Instruction Not Supported");
            }
            Integer temp[] = new Integer[registers.length+1];
            temp = registers.clone();
            temp[32] =a;
            data.add(temp.clone());
        }
		return data;
    }

    private static int findAddress(String str) {
        String address = str.replace('(', ' ');
        address = address.replace(')', ' ');
        String [] val = address.split(" ");
        int offset = Integer.parseInt(val[0].trim());
        int base_reg = findReg(val[1].trim());
        int reg_val = registers[base_reg];

        return reg_val + offset;
    }

    private static void NEG(String []inst) {
        int destination = findReg(inst[1]);
        int source = findReg(inst[2]);

        registers[destination] = -(registers[source]);
    }

    private static void ADD(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = findReg(inst[3]);

        registers[destination] = registers[source1] + registers[source2];
    }

    private static void ADDI(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = Integer.parseInt(inst[3]);

        registers[destination] = registers[source1] + source2;
    }

    private static void LA(String []inst) {
        int destination = findReg(inst[1]);
        int add = findAddress(inst[2]);

        try {
            registers[destination] = Memory.get(add);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void MOVE(String []inst) {
        int destination = findReg(inst[1]);
        int source = findReg(inst[2]);

        registers[destination] = registers[source];
    }

    private static void MUL(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = findReg(inst[3]);

        registers[destination] = registers[source1] * registers[source2];
    }

    private static void DIV(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = findReg(inst[3]);

        int reg1 = registers[source1];
        int reg2 = registers[source2];

        if(reg2 == 0) {
            System.out.println("Divide by zero exception");
            return ;
        }

        registers[destination] = reg1 / reg2;
    }

    private static void AND(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = findReg(inst[3]);

        registers[destination] = registers[source1] & registers[source2];
    }

    private static void ANDI(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = Integer.parseInt(inst[3]);

        registers[destination] = registers[source1] & source2;
    }

    private static void NOR(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = findReg(inst[3]);

        registers[destination] = ~(registers[source1] | registers[source2]);
    }

    private static void NOT(String []inst) {
        int destination = findReg(inst[1]);
        int source = findReg(inst[2]);

        registers[destination] =  ~(registers[source]);
    }

    private static void OR(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = findReg(inst[3]);

        registers[destination] = registers[source1] | registers[source2];
    }

    private static void ORI(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = Integer.parseInt(inst[3]);

        registers[destination] = registers[source1] | source2;
    }

    private static void XOR(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = findReg(inst[3]);

        registers[destination] = registers[source1] ^ registers[source2];
    }

    private static void XORI(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = Integer.parseInt(inst[3]);

        registers[destination] = registers[source1] + source2;
    }

    private static void MOVN(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = findReg(inst[3]);

        if(source2 != 0) {
            registers[destination] = source1;
        }
    }

    private static void MOVZ(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = findReg(inst[3]);

        if(source2 == 0) {
            registers[destination] = registers[source1];
        }
    }

    private static void SLT(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = findReg(inst[3]);

        if(source1 < source2) {
            registers[destination] = 1;
        } else {
            registers[destination] = 0;
        }
    }
    
    private static void SLTI(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = Integer.parseInt(inst[3]);

        if(registers[source1] < source2) {
            registers[destination] = 1;
        } else {
            registers[destination] = 0;
        }
    }

    private static void SUB(String []inst) {
        int destination = findReg(inst[1]);
        int source1 = findReg(inst[2]);
        int source2 = findReg(inst[3]);

        registers[destination] = registers[source1] - registers[source2];
    }

    private static void LI(String []inst) {
        int reg = findReg(inst[1]);
        int val = Integer.parseInt(inst[2]);
        registers[reg] = val;
    }

    private static void SD(String []inst) {
        // Store data into memory
        int reg = findReg(inst[1]);
        int add = findAddress(inst[2]);

        boolean flag = Memory.set(add, registers[reg]);
        if(flag) {
            System.out.println("Set successful");
        } else {
            System.out.println("Set unsucessful");
        }
    }

    public static void printAllRegisters() {
        System.out.println("Content in all registers: ");
        for(int a = 0; a < registers.length; a++) {
            System.out.print("R" + a + ": " + registers[a] + " ");
        }
    }

    private static int BEQ(String []inst) {
        int reg1 = findReg(inst[1]);
        int reg2 = findReg(inst[2]);
        String flag = inst[3];

        if(registers[reg1] == registers[reg2]) {
            return Data.flags.get(flag);
        }

        return -1;
    }

    private static int BEQZ(String []inst) {
        int reg1 = findReg(inst[1]);
        String flag = inst[2];

        if(registers[reg1] == 0) {
            return Data.flags.get(flag);
        }

        return -1;
    }

    private static int BGEZ(String []inst) {
        int reg = findReg(inst[1]);
        String flag = inst[2];

        if(registers[reg] >= 0) {
            return Data.flags.get(flag);
        }

        return -1;
    }

    private static int BGTZ(String []inst) {
        int reg = findReg(inst[1]);
        String flag = inst[2];

        if(registers[reg] > 0) {
            return Data.flags.get(flag);
        }

        return -1;
    }

    private static int BLEZ(String []inst) {
        int reg = findReg(inst[1]);
        String flag = inst[2];

        if(registers[reg] <= 0) {
            return Data.flags.get(flag);
        }

        return -1;
    }

    private static int BLTZ(String []inst) {
        int reg = findReg(inst[1]);
        String flag = inst[2];

        if(registers[reg] < 0) {
            return Data.flags.get(flag);
        }

        return -1;
    }

    private static int B(String []inst) {
        String flag = inst[1];

        if(Data.flags.containsKey(flag)) {
            return Data.flags.get(flag);
        }

        return -1;
    }

    private static int BNE(String []inst) {
        int reg1 = findReg(inst[1]);
        int reg2 = findReg(inst[2]);
        String flag = inst[3];

        if(registers[reg1] != registers[reg2]) {
            return Data.flags.get(flag);
        }

        return -1;
    }

    private static int BNEZ(String []inst) {
        int reg = findReg(inst[1]);
        String flag = inst[2];

        if(registers[reg] != 0) {
            return Data.flags.get(flag);
        }

        return -1;
    }

    private static void NOP() {
        // DO NOTHING
    }

    private static int findReg(String val) {
        StringBuilder str = new StringBuilder();
        for(int a = 1; a < val.length(); a++) {
            str.append(val.charAt(a));
        }

        return Integer.parseInt(str.toString());
    }
}
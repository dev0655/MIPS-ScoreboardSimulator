package com.umbc.mips.service;
// TODO:
// [ ] Implement BEQ inst
// [ ] Implement LA/LD inst
// [ ] Implement NEGATE


class Execute {
    public static int [] registers = new int[32];
    private static int [] spec = new int[4];

    public static void execute() {
        // Loop on all instructions
        for(int a = 0; a < Data.instructions.length; a++) {
            // TODO: iterate over all the instructions and based on the
            // kind of the instruction execute them;
            switch(Data.instructions[a][0]) {
                case "LI":
                    System.out.println("Instruction LI");
                    LI(Data.instructions[a]);
                    printAllRegisters();
                    break;
                case "LA":
                    System.out.println("Instruction LA");
                    LA(Data.instructions[a]);
                    printAllRegisters();
                    break;
                case "ADDI":
                    System.out.println("Instruction ADDI");
                    // TODO: implement ADDI
                    // ADD(Data.instructions[a]);
                    break;
                case "MUL.D":
                    System.out.println("Instruction MUL.D");
                    MUL(Data.instructions[a]);
                    printAllRegisters();
                    break;
                case "S.D":
                    System.out.println("Instruction S.D");
                    SD(Data.instructions[a]);
                    printAllRegisters();
                    break;
                case "DIV.D":
                    System.out.println("Instruction DIV.D");
                    DIV(Data.instructions[a]);
                    printAllRegisters();
                    break;
                case "ADD":
                    System.out.println("Instruction ADD");
                    ADD(Data.instructions[a]);
                    break;
                case "SW":
                    System.out.println("Instruction SW");
                    // TODO: implement SW
                    // SW(Data.instructions[a]);
                    break;
                case "DSUBI":
                    System.out.println("Instruction DSUBI");
                    // TODO: implement DSUBI
                    // DSUBI(Data.instructions[a]);
                    break;
                case "BEQ":
                    System.out.println("Instruction BEQ");
                    // TODO: implement BEQ
                    // BEQ(Data.instructions[a]);
                    break;
                case "HLT":
                    System.out.println("Instruction HLT");
                    // TODO: see if HLT needs to be implemented or left out ?
                    break;
                case "MOVE":
                    System.out.println("Instruction MOVE");
                    MOVE(Data.instructions[a]);
                    printAllRegisters();
                    break;
                case "AND":
                    System.out.println("Instruction AND");
                    AND(Data.instructions[a]);
                    break;
                case "OR":
                    System.out.println("Instruction OR");
                    OR(Data.instructions[a]);
                    break;
                case "XOR":
                    System.out.println("Instruction XOR");
                    XOR(Data.instructions[a]);
                    break;
                case "MOVN":
                    System.out.println("Instruction MOVN");
                    MOVN(Data.instructions[a]);
                    break;
                default:
                    System.out.println("Instruction Not Supported");
            }
        }
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

    private static void NEGU(String []inst) {
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

        registers[destination] = registers[source1] / registers[source2];
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
            registers[destination] = source2;
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

    private static void LD(String []inst) {
        // Load from memory
        int reg = findReg(inst[1]);
        int add = findAddress(inst[2]);

        try {
            registers[reg] = Memory.get(add);
        } catch(Exception e) {
            System.out.println(e);
        }
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

    private static int findReg(String val) {
        StringBuilder str = new StringBuilder();
        for(int a = 1; a < val.length(); a++) {
            str.append(val.charAt(a));
        }

        return Integer.parseInt(str.toString());
    }
}

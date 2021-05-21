package com.umbc.mips.service;

class Memory {
    private static int MEM_SIZE = 1024;
    private static char []mem = new char[MEM_SIZE];

    public static boolean set(int pos, int value) {
        if(pos >= MEM_SIZE) {
            System.out.println("Only 1024 bytes of memory available. (0-" + (MEM_SIZE - 1) + "\n");
            return false ; 
        }

        mem[pos] = (char) value;
        return true;
    }

    public static int get(int pos) throws Exception {
        if(pos >= MEM_SIZE) {
            System.out.println("Only 1024 bytes of memory available. (0-" + (MEM_SIZE - 1) + "\n");
            throw new Exception("Non-addressible memory address\n");
        }

        return (int)mem[pos];
    }
}

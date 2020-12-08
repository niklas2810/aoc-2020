package com.niklasarndt.aoc.twentytwenty.solutions.handheld;

import com.niklasarndt.aoc.twentytwenty.Utils;

public class Instruction {

    private final Instruction.Type type;
    private final int amount;

    private int visited = 0;

    public Instruction(Type type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public Instruction(String line) {
        type = Type.parse(line.substring(0, line.indexOf(" ")));
        amount = Utils.parseInt(line.substring(line.indexOf(" ")+1), Integer.MAX_VALUE);
    }

    public void reset() {
        visited = 0;
    }

    public void visit() {
        ++visited;
    }

    public Type getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public int getVisited() {
        return visited;
    }

    @Override
    public String toString() {
        return String.format("%s %c%d", type.toString(), amount < 0 ? '-' : '+', Math.abs(amount));
    }

    public enum Type {
        NOP, ACC, JMP, UNKNOWN;

        public static Type parse(String in) {
            in = in.toUpperCase();

            if(in.startsWith("NOP"))
                return NOP;
            if(in.startsWith("ACC"))
                return ACC;
            if(in.startsWith("JMP"))
                return JMP;
            return UNKNOWN;
        }
    }
}

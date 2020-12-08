package com.niklasarndt.aoc.twentytwenty.solutions.handheld;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    private final List<Instruction> instructions = new ArrayList<>();
    private int accumulator = 0;
    private int instructionPointer = 0;

    private final List<Integer> history = new ArrayList<>();

    public Interpreter() {}

    public Interpreter(List<String> code) {
        for(String line : code)
            instructions.add(new Instruction(line));
    }


    public void setCode(List<Instruction> code) {
        reset();
        instructions.clear();
        instructions.addAll(code);
    }

    public void reset() {
        instructionPointer = 0;
        accumulator = 0;
        for (Instruction i : instructions) {
            i.reset();
        }
        history.clear();
    }

    public void setInstructionPointer(int p) {
        this.instructionPointer = p;
    }

    public void execute(boolean silent) {
        reset();
        while (executeOneLine(silent) == State.OKAY);
    }

    public State executeOneLine(boolean silent) {
        if(!isPointerInCode()) {
            if(!silent) System.out.println("INVALID CODE POINTER " + instructionPointer);
            return State.END_OF_CODE;
        }

        if(history.contains(instructionPointer)) {
            if(!silent) System.out.println("Visited twice: " + current() + " | L" + (instructionPointer+1));
            return State.DUPLICATION;
        }

        current().visit();

        int indexBeforeExec = instructionPointer;
        interpret(instructionPointer);

        if(instructionPointer == indexBeforeExec)
            ++instructionPointer;

        if(!isPointerInCode()) {
            if(!silent) System.out.println("REACHED END OF CODE AT LINE " + instructionPointer);
            return State.END_OF_CODE;
        }

        return State.OKAY;
    }

    private void interpret(int pointer) {
        if(!isPointerInCode())
            throw new IllegalStateException("Pointer out code bounds");

        history.add(0, pointer);

        Instruction i = at(pointer);

        switch (i.getType()) {
            case ACC -> acc(i.getAmount());
            case JMP -> jmp(i.getAmount());
        }

    }

    private void acc(int amount) {
        accumulator += amount;
    }

    private void jmp(int amount) {
        instructionPointer += amount;
    }


    public boolean validPointer(int p) {
        return p >= 0 && p < instructions.size();
    }

    public boolean isPointerInCode() {
        return validPointer(instructionPointer);
    }


    public Instruction current() {
        return at(instructionPointer);
    }

    public Instruction at(int i) {
        return instructions.get(i);
    }

    public void printHistory(int amount) {

        int end = Math.min(history.size(), amount);

        for(int i = 0; i < end; i++) {
            System.out.printf("%s | L%d\n", at(history.get(i)), history.get(i));
        }
    }

    public int getMostRecent() {
        return history.size() > 0 ? history.get(0) : 0;
    }

    public void replace(int index, Instruction newInstruction) {
        if(!validPointer(index))
            throw new IllegalArgumentException("Index out of code bounds");

        instructions.set(index, newInstruction);
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public int getAcc() {
        return accumulator;
    }

    public void printCode() {
        for(int i = 0; i < instructions.size(); i++) {
            System.out.printf("%s | L%d\n", at(i), i);
        }
    }

    public enum State {
        OKAY, END_OF_CODE, DUPLICATION;
    }
}

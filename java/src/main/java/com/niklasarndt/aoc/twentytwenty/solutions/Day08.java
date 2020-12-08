package com.niklasarndt.aoc.twentytwenty.solutions;

import com.niklasarndt.aoc.twentytwenty.Exercise;
import com.niklasarndt.aoc.twentytwenty.Utils;
import com.niklasarndt.aoc.twentytwenty.solutions.handheld.Instruction;
import com.niklasarndt.aoc.twentytwenty.solutions.handheld.Interpreter;

@Exercise(8)
public class Day08 {

    public Day08() {
        Interpreter interpreter = new Interpreter(Utils.readFile("../input/day08.txt"));
        System.out.println("PART ONE: ");
        interpreter.execute(false);

        System.out.println("PART TWO: ");


        for(int i = 0; i < interpreter.getInstructions().size(); i++) {
            Instruction.Type current = interpreter.getInstructions().get(i).getType();
            int value = interpreter.getInstructions().get(i).getAmount();
            if(current != Instruction.Type.NOP && current != Instruction.Type.JMP)
                continue;

            Instruction.Type inverse = current == Instruction.Type.NOP ? Instruction.Type.JMP : Instruction.Type.NOP;

            interpreter.replace(i, new Instruction(inverse, value));

            interpreter.reset();
            interpreter.execute(true);

            if(interpreter.executeOneLine(true) == Interpreter.State.END_OF_CODE)
                System.out.println("FOUND for " + i + ": " + interpreter.getAcc());

            interpreter.replace(i, new Instruction(current, value));

        }

    }
}

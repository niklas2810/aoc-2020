package com.niklasarndt.aoc.twentytwenty.solutions;

import com.niklasarndt.aoc.twentytwenty.Exercise;
import com.niklasarndt.aoc.twentytwenty.Utils;

import java.util.Arrays;
import java.util.List;

@Exercise(6)
public class Day06 {

    public Day06() {
        List<String> lines = Utils.readFile("../input/day06.txt");

        StringBuilder buffer = new StringBuilder();

        long firstCounter = 0;
        long secondCounter = 0;

        for (String line : lines) {
            if (line.trim().length() == 0) {
                String groupAnswers = buffer.toString();
                firstCounter += countForFirstPart(groupAnswers);
                secondCounter += countForSecondPart(groupAnswers);

                buffer.setLength(0);
                continue;
            }
            buffer.append(line.trim()).append("\n");

        }

        firstCounter += countForFirstPart(buffer.toString());
        secondCounter += countForSecondPart(buffer.toString());

        System.out.println("PART ONE: " + firstCounter); //6714
        System.out.println("PART TWO: " + secondCounter); //3435

    }

    private long countForSecondPart(String input) {
        List<String> lines = Arrays.asList(input.split("\n"));

        //1. Parse lines at \n
        //2. For every char in first line, check if this char is present in
        //   all other lines as well
        //3. Count the number of valid chars in the first line
        return lines.get(0).chars().filter(c -> lines.stream()
                .filter(line -> line.indexOf(c) >= 0).count() == lines.size())
                .count();

    }

    private long countForFirstPart(String input) {
        return input.chars().filter(i -> i >= 'a' && i <= 'z').distinct().count();
    }
}

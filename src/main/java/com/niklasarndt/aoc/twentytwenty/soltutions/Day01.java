package com.niklasarndt.aoc.twentytwenty.soltutions;

import com.niklasarndt.aoc.twentytwenty.Exercise;
import com.niklasarndt.aoc.twentytwenty.Utils;

import java.util.List;
import java.util.stream.Collectors;

@Exercise(1)
public class Day01 {

    public Day01() {
        List<Integer> els = Utils.readResource("day01.txt").stream()
                .map(Integer::valueOf).collect(Collectors.toList());

        System.out.println("PART ONE:\n\n");
        //Part One
        els.forEach(first -> els.forEach(second -> {
            if (first + second == 2020)
                System.out.printf("%d + %d = 2020, Multiplied: %d\n", first, second, first * second);
        }));

        System.out.println("\n\n\nPART TWO: ");
        //Part Two
        els.forEach(first -> els.forEach(second -> els.forEach(third -> {
            if (first + second + third == 2020)
                System.out.printf("%d + %d + %d = 2020, Multiplied: %d\n", first, second, third, first * second * third);
        })));
    }
}

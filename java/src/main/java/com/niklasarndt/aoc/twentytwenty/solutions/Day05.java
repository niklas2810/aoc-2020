package com.niklasarndt.aoc.twentytwenty.solutions;

import com.niklasarndt.aoc.twentytwenty.Exercise;
import com.niklasarndt.aoc.twentytwenty.Utils;

import java.util.List;
import java.util.stream.Collectors;

@Exercise(5)
public class Day05 {

    public Day05() {

        List<Integer> ids = Utils.readFile("../input/day05.txt").stream()
                .map(this::calculateSeatId).sorted(Integer::compareTo)
                .collect(Collectors.toList());

        int min = ids.get(0);
        int max = ids.get(ids.size()-1);

        System.out.println("PART ONE: " + min);

        for(int i = min+1; i < max; i++) {
            if(!ids.contains(i))
                System.out.println("PART TWO: " + i);
        }
    }


    private int calculateSeatId(String passport) {
        if (passport.length() != 10) {
            System.out.println("ERROR: Invalid input " + passport);
            return -1;
        }

        int row = parsePositioning(passport.substring(0, 7), 'B');
        int col = parsePositioning(passport.substring(7), 'R');

        return (row * 8) + col;
    }

    private int parsePositioning(String regionIndicators, char upCounter) {
        int result = 0;
        for (int i = 1; i <= regionIndicators.length(); i++) {
            if (regionIndicators.charAt(i - 1) == upCounter)
                result += 1 << (regionIndicators.length() - i);
        }
        return result;
    }
}

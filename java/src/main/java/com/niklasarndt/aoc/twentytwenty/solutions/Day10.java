package com.niklasarndt.aoc.twentytwenty.solutions;

import com.niklasarndt.aoc.twentytwenty.Exercise;
import com.niklasarndt.aoc.twentytwenty.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Exercise(10)
public class Day10 {

    private final List<Integer> adapters = new ArrayList<>();
    private int ones = 0;
    private int threes = 0;


    public Day10() {
        parseAdapterList("../input/day10.txt");

        int high = adapters.get(adapters.size()-1)+3;
        partOne(0, high);

        System.out.println("PART ONE: " + ones*threes);

        long found = partTwo(0, new long[high], high);
        System.out.println("PART TWO: " + found);

    }

    private void partOne(int value, int end) {
        if(adapters.contains(value+1)) {
            partOne(value+1, end);
            ++ones;
            return;
        }

        if(adapters.contains(value+3) || value+3 == end) {
            partOne(value+3, end);
            ++threes;
        }
    }

    private long partTwo(int value, long[] data, int end) {
        if(value+3 == end)
            return 1;

        long paths = 0;
        for(int i = value+1; i <= value+3; ++i) {
            if(adapters.contains(i)) {
                if(data[i] == 0) //Path not traversed yet
                    data[i] = partTwo(i, data, end);

                paths += data[i];
            }
        }

        return paths;
    }

    private void parseAdapterList(String filename) {
        ones = 0;
        threes = 0;
        adapters.clear();

        adapters.addAll(Utils.readFile(filename).stream().map(Integer::valueOf).sorted().collect(Collectors.toList()));
    }

}

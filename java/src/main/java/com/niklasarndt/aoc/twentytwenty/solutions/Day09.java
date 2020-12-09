package com.niklasarndt.aoc.twentytwenty.solutions;

import com.niklasarndt.aoc.twentytwenty.Exercise;
import com.niklasarndt.aoc.twentytwenty.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Exercise(9)
public class Day09 {

    private List<Long> data = new ArrayList<>();

    public Day09() {
        checkFile("../input/day09.txt", 25);
    }

    private void checkFile(String filename, int preamble) {
        data.clear();

        data.addAll(Utils.readFile(filename).stream()
                .map(i -> Utils.parseLong(i, Long.MIN_VALUE)).collect(Collectors.toList()));

        int invalidIndex = findInvalidIndex(preamble);
        long invalidValue = data.get(invalidIndex);

        System.out.println("PART ONE: " + invalidValue);

        List<Long> subrange = findSumSubrangeFor(invalidIndex);
        long min = subrange.stream().mapToLong(Long::longValue).min().orElse(0);
        long max = subrange.stream().mapToLong(Long::longValue).max().orElse(0);

        System.out.println("PART TWO: " + (min + max));

    }

    private long sum(int start, int end) {
        long res = 0;
        for (int i = start; i <= end; i++)
            res += data.get(i);
        return res;
    }

    private List<Long> findSumSubrangeFor(int index) {
        List<Long> result = new ArrayList<>();
        long target = data.get(index);

        for (int i = index - 1; i >= 1; --i)
            for (int j = i - 1; j >= 0; --j)
                if (sum(j, i) == target) {
                    result.addAll(data.subList(j, i));
                    return result;
                }

        return result;
    }

    private int findInvalidIndex(int preamble) {
        for (int i = preamble; i < data.size(); ++i)
            if (!hasSum(i, preamble))
                return i;

        return -1;
    }

    private boolean hasSum(int index, int preamble) {
        long target = data.get(index);
        int dest = Math.max(0, index - preamble - 1);

        for (int i = index - 1; i >= dest; --i)
            for (int j = i - 1; j >= dest; --j)
                if (data.get(i) + data.get(j) == target && data.get(i) - data.get(j) != 0)
                    return true;

        return false;
    }

}

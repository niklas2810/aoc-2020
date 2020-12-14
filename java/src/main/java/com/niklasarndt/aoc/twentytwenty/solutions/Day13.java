package com.niklasarndt.aoc.twentytwenty.solutions;

import com.niklasarndt.aoc.twentytwenty.Exercise;
import com.niklasarndt.aoc.twentytwenty.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Exercise(13)
public class Day13 {

    public Day13() {
        List<String> lines = Utils.readFile("../input/day13.txt");

        int arrival = Utils.parseInt(lines.get(0), -1);
        if (arrival < 0)
            throw new IllegalStateException("Arrival time was not parsed");

        String[] options = lines.get(1).split(",");
        List<Bus> busses = new ArrayList<>();

        for (int i = 0; i < options.length; ++i)
            if(!options[i].equals("x"))
                busses.add(new Bus(Utils.parseInt(options[i], -1), i, arrival));


        Bus lowest = busses.stream().min(Comparator.comparingInt(o -> o.closest)).orElse(null);

        if(lowest == null)
            throw new IllegalStateException("Could not find lowest bus");

        int waitTime = lowest.closest - arrival;
        System.out.println("PART ONE: " + (lowest.id * waitTime)); //2382


        long[] ids = busses.stream().mapToLong(i -> i.id).toArray();
        long[] offset = busses.stream().mapToLong(i -> (i.id - i.index) % i.id).toArray();
        System.out.println("PART TWO: " + calculateRemainder(ids, offset));
    }

    private class Bus {
        public final int id;
        public final int index;
        public final int closest;

        public Bus(int id, int index, int arrival) {
            this.id = id;
            this.index = index;
            this.closest = findClosestArrival(id, arrival);
        }
    }

    private int findClosestArrival(int pattern, int arrival) {
        int res = pattern;
        while (res < arrival)
            res += pattern;
        return res;
    }

    /**
     * This code is from https://rosettacode.org/wiki/Chinese_remainder_theorem#Java.
     *
     * I modified the code so that it works with longs.
     *
     * If you want to know more about the theorem, visit https://en.wikipedia.org/wiki/Chinese_remainder_theorem.
     */
    public static long calculateRemainder(long[] n, long[] a) {
        long prod = Arrays.stream(n).reduce(1, (i, j) -> i * j);

        long p, sm = 0;
        for (int i = 0; i < n.length; i++) {
            p = prod / n[i];
            sm += a[i] * mulInv(p, n[i]) * p;
        }
        return sm % prod;
    }

    private static long mulInv(long a, long b) {
        long b0 = b;
        long x0 = 0;
        long x1 = 1;

        if (b == 1)
            return 1;

        while (a > 1) {
            long q = a / b;
            long amb = a % b;
            a = b;
            b = amb;
            long xqx = x1 - q * x0;
            x1 = x0;
            x0 = xqx;
        }

        if (x1 < 0)
            x1 += b0;

        return x1;
    }
}

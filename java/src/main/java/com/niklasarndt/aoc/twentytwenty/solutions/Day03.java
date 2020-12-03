package com.niklasarndt.aoc.twentytwenty.solutions;

import com.niklasarndt.aoc.twentytwenty.Exercise;
import com.niklasarndt.aoc.twentytwenty.Utils;

import java.util.List;

@Exercise(3)
public class Day03 {

    public Day03() {
        List<String> lines = Utils.readFile("../input/day03.txt");

        long t0 = findTrees(lines, 1, 1);
        long t1 = findTrees(lines, 3, 1);
        long t2 = findTrees(lines, 5, 1);
        long t3 = findTrees(lines, 7, 1);
        long t4 = findTrees(lines, 1, 2);

        System.out.println("PART ONE: " + t1);
        System.out.println("PART TWO: " + (t0*t1*t2*t3*t4));
    }

    public long findTrees(List<String> lines, int xPerMove, int yPerMove) {
        int trees = 0;
        int x = 0;
        for(int y = 0; y < lines.size(); y+=yPerMove) {
            if(isTree(lines.get(y), x))
                trees++;
            x += xPerMove;
        }
        return trees;
    }

    private boolean isTree(String line, int index) {
        return line.charAt(index%line.length()) == '#';
    }
}

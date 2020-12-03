package com.niklasarndt.aoc.twentytwenty.solutions;

import com.niklasarndt.aoc.twentytwenty.Exercise;
import com.niklasarndt.aoc.twentytwenty.Utils;

import java.util.List;

@Exercise(2)
public class Day02 {

    public Day02() {
        List<String> lines = Utils.readFile("../input/day02.txt");
        int first = 0;
        int second = 0;
        for (String line : lines) {
            Setup s = new Setup(line);
            if (matchesFirstPasswordPolicy(s)) first++;
            if (matchesSecondPasswordPolicy(s)) second++;
        }

        System.out.println("PART ONE: " + first); //410
        System.out.println("PART TWO: " + second); //694
    }

    private int occurrenceOfChar(String input, char c) {
        return (int) input.chars().filter(i -> i == c).count();
    }

    private boolean matchesFirstPasswordPolicy(Setup s) {
        int occ = occurrenceOfChar(s.pw, s.policy.c);
        return occ >= s.policy.min && occ <= s.policy.max;
    }

    private boolean matchesSecondPasswordPolicy(Setup s) {
        return (s.pw.length() >= s.policy.min && s.pw.charAt(s.policy.min - 1) == s.policy.c)
                ^ (s.pw.length() >= s.policy.max && s.pw.charAt(s.policy.max - 1) == s.policy.c);
    }
    
    class Setup {
        private final String pw;
        private final PasswordPolicy policy;
        
        public Setup(String line) {
            pw = line.substring(line.indexOf(":") + 1).trim();
            policy = new PasswordPolicy(line.substring(0, line.indexOf(":")).trim());
        }
    }

    class PasswordPolicy {

        private final int min;
        private final int max;
        private final char c;

        public PasswordPolicy(String line) {
            min = Utils.parseInt(line.substring(0, line.indexOf("-")), -1);
            max = Utils.parseInt(line.substring(line.indexOf("-") + 1, line.indexOf(" ")), -1);
            c = line.charAt(line.indexOf(' ') + 1);
        }
    }
}

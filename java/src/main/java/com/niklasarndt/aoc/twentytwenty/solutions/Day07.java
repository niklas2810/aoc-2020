package com.niklasarndt.aoc.twentytwenty.solutions;

import com.niklasarndt.aoc.twentytwenty.Exercise;
import com.niklasarndt.aoc.twentytwenty.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Exercise(7)
public class Day07 {
    
    private final List<Rule> rules = new ArrayList<>();
    private final List<String> candidatesForGold = new ArrayList<>();

    public Day07() {
        List<String> lines = Utils.readFile("../input/day07.txt");
        for (String line : lines) {
            parseRule(line);
        }

        System.out.println("PART ONE: " + solvePartOne());
        System.out.println("PART TWO: " + solvePartTwo());
    }

    private int solvePartTwo() {
        Optional<Rule> gold = rules.stream().filter(i -> i.bag.equals("shiny gold")).findFirst();
        if (gold.isEmpty()) {
            return -1;
        }
        return countOfBagsFor(gold.get()) - 1;
    }

    private int countOfBagsFor(Rule rule) {
        AtomicInteger result = new AtomicInteger(0);

        rule.mayContain.forEach((key, value) -> {
            rules.stream().filter(i -> i.bag.equals(key)).findFirst().ifPresent(p ->
                    result.addAndGet(value * countOfBagsFor(p)));
        });
        return 1 + result.get();
    }

    private int solvePartOne() {
        candidatesForGold.add("shiny gold");
        int old = -1;

        while (old != candidatesForGold.size()) {
            old = candidatesForGold.size();
            for (int i = 0; i < candidatesForGold.size(); i++) {
                appendDirectCandidates(candidatesForGold.get(i));
            }
        }

        return candidatesForGold.size()-1;
    }

    private void appendDirectCandidates(String candidate) {
        for (Rule r : rules) {
            if (r.canContain(candidate) && !candidatesForGold.contains(r.bag)) {
                candidatesForGold.add(r.bag);
            }
        }
    }

    private void parseRule(String line) {
        String trimmer = "bags contain";

        String src = line.substring(0, line.indexOf(trimmer)).trim();

        if (line.endsWith("no other bags.")) {
            rules.add(new Rule(src));
            return;
        }

        String[] parts = line.substring(line.indexOf(trimmer) + trimmer.length(), line.length() - 1).split(",");

        Rule result = new Rule(src);
        for (String part : parts) {
            part = part.trim();
            result.add(part.substring(part.indexOf(" ") + 1, part.indexOf("bag")).trim(),
                    Utils.parseInt(part.substring(0, part.indexOf(" ")), -1));
        }
        rules.add(result);
    }

    class Rule {
        private final String bag;
        private final HashMap<String, Integer> mayContain;

        public Rule(String bag) {
            this.bag = bag;
            this.mayContain = new HashMap<>();
        }

        public Rule(String bag, HashMap<String, Integer> mayContain) {
            this.bag = bag;
            this.mayContain = mayContain;
        }

        public void add(String bag, int amount) {
            mayContain.put(bag, amount);
        }

        public boolean canContain(String bag) {
            return mayContain.containsKey(bag);
        }
    }
}

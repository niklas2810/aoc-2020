package com.niklasarndt.aoc.twentytwenty.solutions;

import com.niklasarndt.aoc.twentytwenty.Exercise;
import com.niklasarndt.aoc.twentytwenty.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Exercise(4)
public class Day04 {

    private static final String[] REQUIRED = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    private static final List<String> EYECOLORS = new ArrayList<>(List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));


    private final Pattern colorPattern = Pattern.compile("^#[a-f0-9]{6}$");
    private final Pattern pidPattern = Pattern.compile("^(0[0-9]{9}|[0-9]{9})$");

    public Day04() {

        List<String> lines = Utils.readFile("../input/day04.txt");

        StringBuilder buffer = new StringBuilder();

        List<String> rawPassports = new ArrayList<>();

        for (String line : lines) {
            if (line.trim().length() == 0) {
                rawPassports.add(buffer.toString().replace("\n", " ").trim());
                buffer.setLength(0);
            } else {
                buffer.append(line).append(" ");
            }
        }
        rawPassports.add(buffer.toString().replace("\n", " ").trim());


        int first = 0;
        int second = 0;
        for (String raw : rawPassports) {
            if (isValidPassport(raw, 1))
                first++;
            if (isValidPassport(raw, 2))
                second++;
        }

        System.out.println("PART ONE: " + first); //196
        System.out.println("PART TWO: " + second); //114
    }

    private boolean isValidPassport(String raw, int part) {
        String[] parts = raw.split(" ");

        Map<String, String> data = new HashMap<>();
        for (String bit : parts) {
            if (!bit.contains(":")) {
                continue;
            }

            data.put(bit.substring(0, bit.indexOf(":")), bit.substring(bit.indexOf(":") + 1));
        }

        for (String req : REQUIRED) {
            if (!data.containsKey(req)) {
                return false;
            }
        }

        if (part == 1)
            return true;

        int byr = Utils.parseInt(data.get("byr"), -1);
        int iyr = Utils.parseInt(data.get("iyr"), -1);
        int eyr = Utils.parseInt(data.get("eyr"), -1);

        String rawHgt = data.get("hgt");
        boolean inch = rawHgt.contains("in");
        if(!inch && !rawHgt.contains("cm")) {
            System.out.println("INVALID HEIGHT: " + rawHgt);
            return false;
        }
        int hgt = Utils.parseInt(rawHgt.substring(0, inch ? rawHgt.indexOf("in") : rawHgt.indexOf("cm")), -1);

        if (byr < 1920 || byr > 2002)
            return false;
        if (iyr < 2010 || iyr > 2020)
            return false;
        if (eyr < 2020 || eyr > 2030)
            return false;

        if (inch && (hgt < 59 || hgt > 76))
            return false;

        if (!inch && (hgt < 150 || hgt > 193))
            return false;

        if (!colorPattern.matcher(data.get("hcl")).matches()) {
            System.out.println("INVALID COLOR: " + data.get("hcl"));
            return false;
        }

        if (!EYECOLORS.contains(data.get("ecl"))) {
            System.out.println("INVALID EYE: " + data.get("ecl"));
            return false;
        }

        if (!pidPattern.matcher(data.get("pid")).matches()) {
            System.out.println("INVALID PID: " + data.get("pid"));
            return false;
        }

        return true;
    }
}

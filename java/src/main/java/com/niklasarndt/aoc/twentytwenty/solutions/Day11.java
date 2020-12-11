package com.niklasarndt.aoc.twentytwenty.solutions;

import com.niklasarndt.aoc.twentytwenty.Exercise;
import com.niklasarndt.aoc.twentytwenty.Utils;

import java.util.List;

@Exercise(11)
public class Day11 {

    Status[][] seats;
    byte[][] neighborCache;

    int width;
    int height;


    public Day11() {
        List<String> lines = Utils.readFile("../input/day11.txt");

        width = lines.get(0).length();
        height = lines.size();

        seats = new Status[height][width];
        neighborCache = new byte[height][width];

        for (int y = 0; y < height; ++y) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); ++x) {
                char c = line.charAt(x);
                seats[y][x] = c == '.' ? Status.FLOOR : (c == 'L') ? Status.FREE : Status.OCCUPIED;
            }
        }

        resetNeighborCache();

        while (true) {
            if(simulateIteration(false) == 0)
                break;
        }
        System.out.println("PART ONE: " + countOccupiedSeats()); //2476

        reset();

        while (true)
            if(simulateIteration(true) == 0)
                break;

        System.out.println("PART TWO: " + countOccupiedSeats()); //2257
    }

    private void reset() {
        resetNeighborCache();
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                if(seats[y][x] == Status.OCCUPIED)
                    seats[y][x] = Status.FREE;
            }
        }
    }

    private int countOccupiedSeats() {
        int res = 0;

        for (int y = 0; y < height; ++y)
            for (int x = 0; x < width; ++x)
                if (seats[y][x] == Status.OCCUPIED)
                    ++res;

        return res;
    }

    private int simulateIteration(boolean partTwo) {
        calculateNeighborCache(partTwo);


        return applyChanges(partTwo ? 5 :4);
    }

    private int applyChanges(int tolerance) {
        int changes = 0;

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                if (occupied(y, x) && neighborCache[y][x] >= tolerance) {
                    seats[y][x] = Status.FREE;
                    ++changes;
                }
                if (free(y, x) && neighborCache[y][x] == 0) {
                    seats[y][x] = Status.OCCUPIED;
                    ++changes;
                }
            }
        }
        return changes;
    }

    private boolean occupied(int y, int x) {
        if (y < 0 || x < 0 || y >= height || x >= width)
            return false;

        return seats[y][x] == Status.OCCUPIED;
    }

    private boolean free(int y, int x) {
        if (y < 0 || x < 0 || y >= height || x >= width)
            return true;

        return seats[y][x] == Status.FREE;
    }

    private void calculateNeighborCache(boolean partTwo) {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                neighborCache[y][x] = calculateNeighbors(y, x, partTwo);
            }
        }
    }

    private byte calculateNeighbors(int y, int x, boolean partTwo) {
        byte neighbors = 0;
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dy = -1; dy <= 1; ++dy) {
                if (dx == 0 && dy == 0) //Same point
                    continue;

                if(partTwo ? hasOccupiedSeatInDirection(y, x, dy, dx) : occupied(y + dy, x + dx))
                    ++neighbors;
            }
        }

        return neighbors;
    }

    private boolean hasOccupiedSeatInDirection(int y, int x, int dy, int dx) {
        y += dy;
        x += dx;

        while (x >= 0 && y >= 0 && y < height && x < width) {
            if(seats[y][x] == Status.OCCUPIED)
                return true;
            if(seats[y][x] == Status.FREE)
                return false;
            y += dy;
            x += dx;
        }

        return false;
    }

    private void resetNeighborCache() {
        neighborCache = new byte[height][width];

        for (int y = 0; y < height; ++y)
            for (int x = 0; x < width; ++x)
                neighborCache[y][x] = 0;
    }

    enum Status {
        FLOOR('.'), FREE('L'), OCCUPIED('#');

        public final char symbol;

        Status(char symbol) {
            this.symbol = symbol;
        }
    }
}

package com.niklasarndt.aoc.twentytwenty.solutions;

import com.niklasarndt.aoc.twentytwenty.Exercise;
import com.niklasarndt.aoc.twentytwenty.Utils;

import java.awt.*;
import java.util.List;

@Exercise(12)
public class Day12 {

    public Day12() {
        List<String> lines = Utils.readFile("../input/day12.txt");

        Position p = new Position();
        for (String line : lines)
            p = p.performAction(line);

        System.out.println("PART ONE: " + (Math.abs(p.pos.x) + Math.abs(p.pos.y))); //882

        WaypointPosition wp = new WaypointPosition();
        for(String line : lines)
            wp = wp.performAction(line);

        System.out.println("PART TWO: " + (Math.abs(wp.boat.x) + Math.abs(wp.boat.y))); //28885

    }


    private class Position {
        public final Point pos;
        public final Direction dir;

        public Position() {
            this.pos = new Point(0, 0);
            this.dir = Direction.EAST;
        }

        public Position(Point pos, Direction dir) {
            this.pos = pos;
            this.dir = dir;
        }

        public Position performAction(String line) {
            char command = line.trim().charAt(0);
            int amount = Utils.parseInt(line.trim().substring(1), -1);

            return switch (command) {
                case 'F' -> new Position(dir.move(pos, amount), dir);
                case 'L' -> new Position(pos, dir.rotate(360 - amount));
                case 'R' -> new Position(pos, dir.rotate(amount));
                default -> new Position(Direction.byLetter(command).move(pos, amount), dir);
            };
        }
    }

    private class WaypointPosition {

        public final Point waypoint;
        public final Point boat;

        public WaypointPosition() {
            this.waypoint = new Point(10, -1);
            this.boat = new Point(0, 0);
        }

        public WaypointPosition(Point waypoint, Point boat) {
            this.waypoint = waypoint;
            this.boat = boat;
        }

        public WaypointPosition rotateWaypoint(int degrees) {
            double angle = Math.toRadians(degrees);

            int wpx = (int)Math.round(waypoint.x * Math.cos(angle) - waypoint.y * Math.sin(angle));
            int wpy = (int)Math.round(waypoint.x * Math.sin(angle) + waypoint.y * Math.cos(angle));

            return new WaypointPosition(new Point(wpx, wpy), boat);
        }

        private WaypointPosition moveShipForward(int times) {
            return new WaypointPosition(waypoint, new Point(boat.x+(waypoint.x*times), boat.y+(waypoint.y*times)));
        }

        public WaypointPosition performAction(String line) {
            char command = line.trim().charAt(0);
            int amount = Utils.parseInt(line.trim().substring(1), -1);

            return switch (command) {
                case 'F' -> moveShipForward(amount);
                case 'L' -> rotateWaypoint(-amount);
                case 'R' -> rotateWaypoint(amount);
                default -> new WaypointPosition(Direction.byLetter(command).move(waypoint, amount), boat);
            };
        }
    }

    private enum Direction {
        NORTH, EAST, SOUTH, WEST;


        public static Direction byLetter(char c) {
            return switch (c) {
                case 'N' -> NORTH;
                case 'E' -> EAST;
                case 'S' -> SOUTH;
                case 'W' -> WEST;
                default -> throw new IllegalArgumentException("Unexpected switch case " + c);
            };
        }

        public Direction rotate(int degrees) {
            if (degrees == 0)
                return this;
            if (degrees == 90)
                return Direction.values()[(ordinal() + 1) % 4];
            if (degrees == 180)
                return Direction.values()[(ordinal() + 2) % 4];
            if (degrees == 270)
                return Direction.values()[(ordinal() + 3) % 4];

            throw new IllegalArgumentException("Could not parse angle " + degrees);
        }

        public Point move(Point pos, int amount) {
            return switch (this) {
                case NORTH -> new Point(pos.x, pos.y - amount);
                case EAST -> new Point(pos.x + amount, pos.y);
                case SOUTH -> new Point(pos.x, pos.y + amount);
                case WEST -> new Point(pos.x - amount, pos.y);
            };
        }

    }
}

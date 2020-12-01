package com.niklasarndt.aoc.twentytwenty;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    /**
     * Parses a {@link String}, using a fallback value if parsing is not possible.
     *
     * @param input        The input {@link String} which might be a valid double
     * @param defaultValue The fallback value to use if the {@code input} is not a valid double.
     * @return If the input is valid, the input as an {@link Integer}, otherwise the fallback value
     * will be returned.
     */
    public static double parseDouble(String input, double defaultValue) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Parses a {@link String}, using a fallback value if parsing is not possible.
     *
     * @param input        The input {@link String} which might be a valid integer
     * @param defaultValue The fallback value to use if the {@code input} is not a valid integer.
     * @return If the input is valid, the input as an {@link Integer}, otherwise the fallback value
     * will be returned.
     */
    public static int parseInt(String input, int defaultValue) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * @return The text which should be displayed to the user.
     */
    public static String readLine(String prompt) {
        return JOptionPane.showInputDialog(prompt);
    }

    /**
     * @param prompt The text which should be displayed to the user.
     * @return The parsed double, or if the value is invalid, {@link Double#MAX_VALUE}.
     * @see #readLine(String)
     * @see #parseDouble(String, double)
     */
    public static double readDouble(String prompt) {
        return parseDouble(readLine(prompt), Double.MAX_VALUE);
    }

    /**
     * @param prompt The text which should be displayed to the user.
     * @return The parsed double, or if the value is invalid, {@link Double#MAX_VALUE}.
     * @see #readLine(String)
     * @see #parseDouble(String, double)
     */
    public static int readInt(String prompt) {
        return parseInt(readLine(prompt), Integer.MAX_VALUE);
    }

    public static List<String> readResource(String resourcePath) {
        List<String> res = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Utils.class.getClassLoader().getResourceAsStream(resourcePath)))) {
            String line;

            while ((line = reader.readLine()) != null) {
                res.add(line);
            }
        } catch (IOException e) {
            return res;
        }
        return res;
    }
}

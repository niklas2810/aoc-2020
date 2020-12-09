package com.niklasarndt.aoc.twentytwenty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

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
     * Parses a {@link String}, using a fallback value if parsing is not possible.
     *
     * @param input        The input {@link String} which might be a valid long
     * @param defaultValue The fallback value to use if the {@code input} is not a valid long.
     * @return If the input is valid, the input as an {@link Long}, otherwise the fallback value
     * will be returned.
     */
    public static long parseLong(String input, long defaultValue) {
        try {
            return Long.parseLong(input);
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

    private static List<String> readInputStream(InputStream stream) {
        List<String> res = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;

            while ((line = reader.readLine()) != null) {
                res.add(line);
            }
        } catch (IOException e) {
            return res;
        }
        return res;
    }

    public static List<String> readResource(String resourcePath) {
        return readInputStream(Utils.class.getClassLoader().getResourceAsStream(resourcePath));
    }

    public static List<String> readFile(String filepath) {
        try (InputStream stream = new FileInputStream(new File(filepath))) {
            return readInputStream(stream);
        } catch (Exception e) {
            logger.warn("File {} does not exist!", filepath, e);
            return new ArrayList<>();
        }
    }
}

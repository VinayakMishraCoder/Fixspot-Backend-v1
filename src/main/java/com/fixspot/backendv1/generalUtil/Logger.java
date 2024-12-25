package com.fixspot.backendv1.generalUtil;

public class Logger {

    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    // Methods for logging with colors
    public static void info(String message) {
        System.out.println(GREEN + "INFO: " + message + RESET);
    }

    public static void warn(String message) {
        System.out.println(YELLOW + "WARN: " + message + RESET);
    }

    public static void error(String message) {
        System.out.println(RED + "ERROR: " + message + RESET);
    }

    public static void debug(String message) {
        System.out.println(BLUE + "DEBUG: " + message + RESET);
    }

    public static void custom(String message, String colorCode) {
        System.out.println(colorCode + message + RESET);
    }
}

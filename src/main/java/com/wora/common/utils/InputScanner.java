package com.wora.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputScanner {
    private final static Scanner SCANNER = new Scanner(System.in);

    public static Integer scanInt(String message) {
        System.out.print(message);
        return SCANNER.nextInt();
    }

    public static Double scanDouble(String message) {
        System.out.print(message);
        return SCANNER.nextDouble();
    }

    public static Float scanFloat(String message) {
        System.out.print(message);
        return SCANNER.nextFloat();
    }

    public static String scanString(String message) {
        System.out.print(message);
        return SCANNER.nextLine();
    }

    public static void clearBuffer() {
        SCANNER.nextLine();
    }

    public static Date scanDate(String prompt) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Date date = null;

        while (date == null) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return null;
            }

            try {
                date = dateFormat.parse(input);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }

        return date;
    }

    public static Boolean scanBoolean(String prompt) {
        Scanner scanner = new Scanner(System.in);
        Boolean result = null;

        System.out.print(prompt);
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.isEmpty()) {
            return null;
        }

        switch (input) {
            case "true", "t", "yes", "y", "1" -> result = true;
            case "false", "f", "no", "n", "0" -> result = false;
            default -> System.out.println("Invalid input. Please enter true or false.");
        }

        System.out.println("result is boolean : " + result);
        return result;
    }
}

package com.wora.common.utils;

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
}

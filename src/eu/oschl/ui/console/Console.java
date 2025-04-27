package eu.oschl.ui.console;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static void print(String message, ConsoleColor color) {
        System.out.print(color.getCode() + message + ConsoleColor.DEFAULT.getCode());
    }

    public static void print(String message) {
        print(message, ConsoleColor.DEFAULT);
    }

    public static void printLine() {
        print("\n");
    }

    public static String readString(String prompt) {
        print(prompt + " ", ConsoleColor.YELLOW);
        return scanner.nextLine();
    }

    public static int readInt() throws InputMismatchException {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Input was not an integer value");
        }
    }
}

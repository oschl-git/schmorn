package eu.oschl.ui.console;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class provides methods for console input and output operations.
 * It allows printing messages in different colors and reading user input.
 *
 * @author Ondřej Schlaichert
 */
public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a message to the console with the specified color.
     *
     * @param message the message to print
     * @param color the color in which to print the message
     */
    public static void print(String message, ConsoleColor color) {
        System.out.print(color.getCode() + message + ConsoleColor.DEFAULT.getCode());
    }

    /**
     * Prints a message to the console in the default color.
     *
     * @param message the message to print
     */
    public static void print(String message) {
        print(message, ConsoleColor.DEFAULT);
    }


    /**
     * Prints a line break to the console.
     */
    public static void printLine() {
        print("\n");
    }

    /**
     * Reads a string input from the console with a prompt.
     *
     * @param prompt the prompt message to display
     * @return the input string entered by the user
     */
    public static String readString(String prompt) {
        print(prompt + " ", ConsoleColor.YELLOW);
        return scanner.nextLine();
    }

    /**
     * Reads a string input from the console without a prompt.
     *
     * @return the input string entered by the user
     */
    public static int readInt() throws InputMismatchException {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Input was not an integer value");
        }
    }
}

package eu.oschl.ui.console.commands;

import eu.oschl.ui.console.Console;
import eu.oschl.ui.console.ConsoleColor;

import java.util.ArrayList;

/**
 * This command displays a list of all available actions in the console application.
 * It provides a way for users to see what commands they can use.
 *
 * @author Ondřej Schlaichert
 */
public class Help implements Command {
    private final ArrayList<Command> commands;

    public Help(ArrayList<Command> commands) {
        this.commands = commands;
    }

    @Override
    public String[] getTriggers() {
        return new String[]{
                "help",
                "actions",
                "commands",
                "list actions",
                "list commands",
        };
    }

    @Override
    public String getDescription() {
        return "display currently available actions";
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            Console.print("This doesn't make any sense.", ConsoleColor.RED);
            return;
        }

        Console.print("available actions:", ConsoleColor.BG_CYAN);

        for (var command : commands) {
            if (command == this) {
                continue;
            }

            Console.printLine();
            Console.print(" * [" + command.getTriggers()[0] + "]", ConsoleColor.YELLOW);
            Console.print(" - " + command.getDescription(), ConsoleColor.WHITE);
        }
    }
}

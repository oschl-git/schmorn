package eu.oschl.ui.console.commands;

import eu.oschl.ui.console.Console;
import eu.oschl.ui.console.ConsoleColor;

import java.util.ArrayList;

public class Help implements Command {
    private final ArrayList<Command> commands;

    public Help(ArrayList<Command> commands) {
        this.commands = commands;
    }

    @Override
    public String[] getTriggers() {
        return new String[]{
                "help",
                "h"
        };
    }

    @Override
    public String getDescription() {
        return "display currently available actions";
    }

    @Override
    public void execute(String[] args) {
        Console.print("available actions:", ConsoleColor.BG_CYAN);

        for (var command : commands) {
            Console.printLine();
            Console.print(" * [" + command.getTriggers()[0] + "]", ConsoleColor.YELLOW);
            Console.print(" - " + command.getDescription(), ConsoleColor.WHITE);
        }
    }
}

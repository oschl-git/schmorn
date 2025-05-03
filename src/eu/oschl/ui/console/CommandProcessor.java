package eu.oschl.ui.console;

import eu.oschl.textadventure.Game;
import eu.oschl.ui.console.commands.*;
import eu.oschl.ui.console.exceptions.InvalidInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CommandProcessor {
    private ArrayList<Command> commands;

    public CommandProcessor(ArrayList<Command> commands) {
        this.commands = commands;
    }

    public static CommandProcessor create(Game game) {
        var commands = new ArrayList<Command>(List.of(
                new Explore(game),
                new Enter(game),
                new Quit()
        ));

        commands.add(new Help(commands));

        return new CommandProcessor(commands);
    }

    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public void processInput(String input) throws InvalidInput {
        var tokens = input.split(" ");

        if (tokens.length == 0) {
            throw new InvalidInput("Input cannot be empty");
        }

        for (Command command : commands) {
            for (String trigger : command.getTriggers()) {
                if (trigger.equalsIgnoreCase(tokens[0])) {
                    command.execute(Arrays.copyOfRange(tokens, 1, tokens.length));
                    return;
                }
            }
        }

        throw new InvalidInput("Command not found");
    }
}

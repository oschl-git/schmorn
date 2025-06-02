package eu.oschl.ui.console;

import eu.oschl.textadventure.Game;
import eu.oschl.ui.console.commands.*;
import eu.oschl.ui.console.exceptions.InvalidInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class processes user input commands in the console application.
 * It matches input against predefined commands and executes the corresponding action.
 *
 * @author Ondřej Schlaichert
 */
class CommandProcessor {
    private final ArrayList<Command> commands;

    public CommandProcessor(ArrayList<Command> commands) {
        this.commands = commands;
    }

    /**
     * Factory method to create a CommandProcessor with a predefined set of commands.
     *
     * @param game the game instance to be used by the commands
     * @return a new CommandProcessor instance with initialized commands
     */
    public static CommandProcessor create(Game game) {
        var commands = new ArrayList<>(List.of(
                new Explore(game),
                new Enter(game),
                new GoBack(game),
                new Inventory(game),
                new TakeItem(game),
                new UseItem(game),
                new PressButton(game),
                new Slay(game)
        ));

        commands.add(new Help(commands));

        return new CommandProcessor(commands);
    }

    /**
     * Adds a new command to the command processor.
     *
     * @param command the command to be added
     */
    public void addCommand(Command command) {
        this.commands.add(command);
    }

    /**
     * Processes the input command string and executes the corresponding command.
     *
     * @param input the input command string
     * @throws InvalidInput if the input is invalid or no matching command is found
     */
    public void processInput(String input) throws InvalidInput {
        var inputTokens = input.trim().split(" ");

        if (inputTokens.length == 0) {
            throw new InvalidInput("Input cannot be empty");
        }

        for (Command command : commands) {
            for (String trigger : command.getTriggers()) {
                var triggerTokens = trigger.trim().split(" ");

                if (doesInputMatchTrigger(inputTokens, triggerTokens)) {
                    command.execute(Arrays.copyOfRange(inputTokens, triggerTokens.length, inputTokens.length));
                    return;
                }
            }
        }

        throw new InvalidInput("Command not found");
    }

    /**
     * Checks if the input tokens match the trigger tokens.
     *
     * @param inputTokens the tokens from the user input
     * @param triggerTokens the tokens from the command trigger
     * @return true if the input matches the trigger, false otherwise
     */
    private boolean doesInputMatchTrigger(String[] inputTokens, String[] triggerTokens) {
        if (inputTokens.length == 0) {
            return false;
        }

        for (var i = 0; i < triggerTokens.length; i++) {
            if (i >= inputTokens.length) {
                return false;
            }

            if (!inputTokens[i].equalsIgnoreCase(triggerTokens[i])) {
                return false;
            }
        }

        return true;
    }
}

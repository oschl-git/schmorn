package eu.oschl.ui.console;

import eu.oschl.textadventure.Game;
import eu.oschl.ui.console.commands.*;
import eu.oschl.ui.console.exceptions.InvalidInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CommandProcessor {
    private final ArrayList<Command> commands;

    public CommandProcessor(ArrayList<Command> commands) {
        this.commands = commands;
    }

    public static CommandProcessor create(Game game) {
        var commands = new ArrayList<>(List.of(
                new Explore(game),
                new Enter(game),
                new GoBack(game),
                new Inventory(game),
                new TakeItem(game),
                new UseItem(game),
                new PressButton(game),
                new Kill(game),
                new Quit()
        ));

        commands.add(new Help(commands));

        return new CommandProcessor(commands);
    }

    public void addCommand(Command command) {
        this.commands.add(command);
    }

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

package eu.oschl.ui.console.commands;

import eu.oschl.textadventure.Game;
import eu.oschl.textadventure.objects.Button;
import eu.oschl.ui.console.Console;
import eu.oschl.ui.console.ConsoleColor;

/**
 * This command allows the player to press a button in the game.
 * It checks if the specified object is a button and attempts to press it.
 * If the button has already been pressed, it informs the player.
 * If the object is not a button, it informs the player that it cannot be pressed.
 *
 * @author Ondřej Schlaichert
 */
public class PressButton implements Command {
    private final Game game;

    public PressButton(Game game) {
        this.game = game;
    }

    @Override
    public String[] getTriggers() {
        return new String[]{
                "press",
                "push",
                "activate",
                "trigger",
        };
    }

    @Override
    public String getDescription() {
        return "press a button";
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Console.print("What button?", ConsoleColor.RED);
            return;
        }

        for (var object : this.game.getCurrentRoom().getObjects()) {
            if (object.getName().equalsIgnoreCase(String.join(" ", args))) {
                if (object instanceof Button button) {
                    var result = button.press();

                    if (result) {
                        Console.print(button.getPressText(), ConsoleColor.MAGENTA);
                    } else {
                        Console.print("Button ", ConsoleColor.RED);
                        Console.print(button.getName(), ConsoleColor.MAGENTA);
                        Console.print(" has already been pressed.", ConsoleColor.RED);
                    }

                } else {
                    Console.print("It's impossible to press ", ConsoleColor.RED);
                    Console.print(object.getName(), ConsoleColor.MAGENTA);
                    Console.print(". It's not a button.", ConsoleColor.RED);
                }
                return;
            }
        }

        Console.print("That item is not here.", ConsoleColor.RED);
    }
}


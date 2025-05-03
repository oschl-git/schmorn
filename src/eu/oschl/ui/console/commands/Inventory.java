package eu.oschl.ui.console.commands;

import eu.oschl.textadventure.Game;
import eu.oschl.ui.console.Console;
import eu.oschl.ui.console.ConsoleColor;

public class Inventory implements Command {
    private final Game game;

    public Inventory(Game game) {
        this.game = game;
    }

    @Override
    public String[] getTriggers() {
        return new String[]{
                "inventory",
                "items",
        };
    }

    @Override
    public String getDescription() {
        return "see what's in your inventory";
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            Console.print("This doesn't make any sense.", ConsoleColor.RED);
            return;
        }

        var inventory = game.getInventory();

        if (inventory.getItems().isEmpty() && inventory.getWeapon().isEmpty()) {
            Console.print("You don't have any possessions.", ConsoleColor.MAGENTA);
            return;
        }

        Console.print("you are carring the following:", ConsoleColor.BG_MAGENTA);

        if (inventory.getWeapon().isPresent()) {
            Console.printLine();
            Console.print("* " + inventory.getWeapon().get().getName(), ConsoleColor.RED);
            Console.print(", " + inventory.getWeapon().get().getDescription(), ConsoleColor.WHITE);
        }

        for (var item : inventory.getItems()) {
            Console.printLine();
            Console.print("* " + item.getName(), ConsoleColor.MAGENTA);
            Console.print(", " + item.getDescription(), ConsoleColor.WHITE);
        }
    }
}

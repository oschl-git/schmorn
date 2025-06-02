package eu.oschl.ui.console.commands;

import eu.oschl.textadventure.Game;
import eu.oschl.ui.console.Console;
import eu.oschl.ui.console.ConsoleColor;

/**
 * This command allows the player to use an item in the game.
 * It checks if the specified item exists in the player's inventory and attempts to use it.
 * If the item is used successfully, it displays a message; otherwise, it informs the player that the item cannot be used.
 *
 * @author Ondřej Schlaichert
 */
public class UseItem implements Command {
    private final Game game;

    public UseItem(Game game) {
        this.game = game;
    }

    @Override
    public String[] getTriggers() {
        return new String[]{
                "use",
        };
    }

    @Override
    public String getDescription() {
        return "use an item";
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Console.print("What item?", ConsoleColor.RED);
            return;
        }

        var item = this.game.getInventory().getItems().stream()
                .filter(inventoryItem -> inventoryItem.getName().equalsIgnoreCase(String.join(" ", args)))
                .findFirst()
                .orElse(null);

        if (item == null) {
            Console.print("That item does not exist.", ConsoleColor.RED);
            return;
        }

        var result = item.use();

        if (result) {
            Console.print(item.getUseText(), ConsoleColor.MAGENTA);
        } else {
            Console.print("It's impossible to use ", ConsoleColor.RED);
            Console.print(item.getName(), ConsoleColor.MAGENTA);
            Console.print(" here.", ConsoleColor.RED);
        }
    }
}


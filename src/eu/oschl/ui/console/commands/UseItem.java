package eu.oschl.ui.console.commands;

import eu.oschl.textadventure.Game;
import eu.oschl.ui.console.Console;
import eu.oschl.ui.console.ConsoleColor;

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
        return "use an item from your inventory";
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
            Console.print("You don't have that item.", ConsoleColor.RED);
            return;
        }

        var result = item.use();

        if (result) {
            Console.print("Used ", ConsoleColor.MAGENTA);
            Console.print(item.getName(), ConsoleColor.BG_MAGENTA);
            Console.print(".", ConsoleColor.MAGENTA);
        } else {
            Console.print("You can't use ", ConsoleColor.RED);
            Console.print(item.getName(), ConsoleColor.MAGENTA);
            Console.print(" here.", ConsoleColor.RED);
        }
    }
}


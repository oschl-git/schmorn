package eu.oschl.ui.console.commands;

import eu.oschl.textadventure.Game;
import eu.oschl.textadventure.objects.PickableObject;
import eu.oschl.ui.console.Console;
import eu.oschl.ui.console.ConsoleColor;

public class TakeItem implements Command {
    private final Game game;

    public TakeItem(Game game) {
        this.game = game;
    }

    @Override
    public String[] getTriggers() {
        return new String[]{
                "take item",
                "take",
                "grab item",
                "grab",
                "pick up item",
                "pick up",
        };
    }

    @Override
    public String getDescription() {
        return "take an item from the current room";
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Console.print("What item?", ConsoleColor.RED);
            return;
        }

        for (var object : this.game.getCurrentRoom().getObjects()) {
            if (object.getName().equalsIgnoreCase(String.join(" ", args))) {
                if (object instanceof PickableObject item) {
                    item.pickUp();

                    Console.print("Item ");
                    Console.print(item.getName(), ConsoleColor.BG_MAGENTA);
                    Console.print("added to inventory.");
                } else {
                    Console.print("You can't pick up ", ConsoleColor.RED);
                    Console.print(object.getName(), ConsoleColor.MAGENTA);
                    Console.print(".", ConsoleColor.RED);
                }
                return;
            }
        }
    }
}


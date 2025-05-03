package eu.oschl.ui.console.commands;

import eu.oschl.textadventure.Game;
import eu.oschl.textadventure.objects.InventoryItem;
import eu.oschl.textadventure.objects.PickableObject;
import eu.oschl.textadventure.objects.Weapon;
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
                "take",
                "grab",
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
                    var result = item.pickUp();

                    if (item instanceof Weapon) {
                        if (result) {
                            Console.print("You are now carrying ");
                            Console.print(item.getName(), ConsoleColor.BG_MAGENTA);
                            Console.print(" as your weapon.");
                        } else {
                            Console.print("You already have a stronger weapon than this one.", ConsoleColor.RED);
                        }
                    } else {
                        if (result) {
                            Console.print("Item ");
                            Console.print(item.getName(), ConsoleColor.BG_MAGENTA);
                            Console.print(" added to inventory.");
                        } else {
                            Console.print("You are carrying too many items.", ConsoleColor.RED);
                        }
                    }

                } else {
                    Console.print("You can't pick up ", ConsoleColor.RED);
                    Console.print(object.getName(), ConsoleColor.MAGENTA);
                    Console.print(".", ConsoleColor.RED);
                }
                return;
            }
        }

        Console.print("That item is not here.", ConsoleColor.RED);
    }
}


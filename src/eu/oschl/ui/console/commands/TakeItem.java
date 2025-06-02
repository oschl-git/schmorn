package eu.oschl.ui.console.commands;

import eu.oschl.textadventure.Game;
import eu.oschl.textadventure.objects.PickableObject;
import eu.oschl.textadventure.objects.Weapon;
import eu.oschl.ui.console.Console;
import eu.oschl.ui.console.ConsoleColor;

/**
 * This command allows the player to take an item from the current room.
 * It checks if the specified object is pickable and attempts to pick it up.
 * If the item is a weapon, it checks if it is stronger than the current one.
 * If the item cannot be picked up, it informs the player accordingly.
 *
 * @author Ondřej Schlaichert
 */
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
        return "take an item";
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Console.print("What item?", ConsoleColor.RED);
            return;
        }

        if (game.getCurrentRoom().isBlockedByEnemy()) {
            Console.print("The way is blocked. It's impossible to pick up items.", ConsoleColor.RED);
            return;
        }

        for (var object : this.game.getCurrentRoom().getObjects()) {
            if (object.getName().equalsIgnoreCase(String.join(" ", args))) {
                if (object instanceof PickableObject item) {
                    var result = item.pickUp();

                    if (item instanceof Weapon) {
                        if (result) {
                            Console.print("Weapon ", ConsoleColor.MAGENTA);
                            Console.print(item.getName(), ConsoleColor.BG_MAGENTA);
                            Console.print(" obtained.", ConsoleColor.MAGENTA);
                        } else {
                            Console.print("This weapon is weaker than the current one.", ConsoleColor.RED);
                        }
                    } else {
                        if (result) {
                            Console.print("Item ", ConsoleColor.MAGENTA);
                            Console.print(item.getName(), ConsoleColor.BG_MAGENTA);
                            Console.print(" added to inventory.", ConsoleColor.MAGENTA);
                        } else {
                            Console.print("Carrying too many items.", ConsoleColor.MAGENTA);
                        }
                    }

                } else {
                    Console.print("Can't pick up ", ConsoleColor.RED);
                    Console.print(object.getName(), ConsoleColor.MAGENTA);
                    Console.print(".", ConsoleColor.RED);
                }
                return;
            }
        }

        Console.print("That item is not here.", ConsoleColor.RED);
    }
}


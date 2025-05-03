package eu.oschl.ui.console.commands;

import eu.oschl.textadventure.Game;
import eu.oschl.ui.console.Console;
import eu.oschl.ui.console.ConsoleColor;

public class Kill implements Command {
    private final Game game;

    public Kill(Game game) {
        this.game = game;
    }

    @Override
    public String[] getTriggers() {
        return new String[]{
                "slay",
                "kill",
                "attack",
                "fight",
                "defeat",
                "destroy",
        };
    }

    @Override
    public String getDescription() {
        return "fight an enemy";
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Console.print("Who?", ConsoleColor.RED);
            return;
        }

        if (
                this.game.getCurrentRoom().getEnemy().isEmpty() ||
                !this.game.getCurrentRoom().getEnemy().get().getName().equalsIgnoreCase(String.join(" ", args))
        ) {
            Console.print("This enemy is not here.", ConsoleColor.RED);
            return;
        }

        var enemy = this.game.getCurrentRoom().getEnemy().get();

        if (enemy.strength > 0 && game.getInventory().getWeapon().isEmpty()) {
            Console.print(enemy.getName() + " is too strong. You need a weapon.", ConsoleColor.RED);
            return;
        }

        if (enemy.strength > 0 && enemy.strength > game.getInventory().getWeapon().get().getDamage()) {
            Console.print(enemy.getName() + " is too strong. You need a stronger weapon.", ConsoleColor.RED);
            return;
        }

        var result = enemy.kill();

        if (result) {
            if (game.getInventory().getWeapon().isPresent()) {
                Console.print(game.getInventory().getWeapon().get().getAttackText());
            } else {
                Console.print("You attack with your bare hands.");
            }
            Console.printLine();

            Console.print("You have defeated ");
            Console.print(enemy.getName(), ConsoleColor.BG_RED);
            Console.print(".");
        } else {
            Console.print("You can't kill ", ConsoleColor.RED);
            Console.print(enemy.getName(), ConsoleColor.RED);
            Console.print(".", ConsoleColor.RED);
        }
    }
}


package eu.oschl.ui.console.commands;

import eu.oschl.textadventure.Game;
import eu.oschl.textadventure.map.Passage;
import eu.oschl.ui.console.Console;
import eu.oschl.ui.console.ConsoleColor;

public class GoBack implements Command {
    private final Game game;

    public GoBack(Game game) {
        this.game = game;
    }

    @Override
    public String[] getTriggers() {
        return new String[]{
                "go back",
                "return",
                "back",
                "exit",
                "leave",
        };
    }

    @Override
    public String getDescription() {
        return "return to previous location";
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            Console.print("This doesn't make any sense.", ConsoleColor.RED);
            return;
        }

        if (game.getLastPassage().isEmpty()) {
            Console.print("Can't go back, the current room is where it all began.", ConsoleColor.RED);
            return;
        }

        var passage = game.getLastPassage().get();

        var result = passage.passThrough(true);
        if (!result) {
            if (game.getCurrentRoom().getEnemy().isPresent()) {
                Console.print(
                        game.getCurrentRoom().getEnemy().get().getName() + " blocks the way. It is only possible to go back.",
                        ConsoleColor.RED
                );
            } else if (passage.getBlockage().isPresent()) {
                Console.print(passage.getBlockage().get().getDescription(), ConsoleColor.RED);
            } else {
                Console.print("The passage is blocked.", ConsoleColor.RED);
            }
            return;
        }

        game.removeLastPassage();

        printSuccessfulPassage(passage);
    }

    private void printSuccessfulPassage(Passage passage) {
        Console.print("Passed through the ");
        Console.print(passage.getName(), ConsoleColor.YELLOW);
        Console.print(" and entered ");
        Console.print(game.getCurrentRoom().getName(), ConsoleColor.BLUE);
        Console.print(".");

        if (game.getCurrentRoom().isBlockedByEnemy()) {
            Console.printLine();
            Console.print("...", ConsoleColor.WHITE);
            Console.printLine();
            Console.print("There is somebody in here.", ConsoleColor.WHITE);
        }
    }
}

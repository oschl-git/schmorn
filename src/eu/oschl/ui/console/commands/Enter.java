package eu.oschl.ui.console.commands;

import eu.oschl.textadventure.Game;
import eu.oschl.textadventure.map.Passage;
import eu.oschl.textadventure.map.Room;
import eu.oschl.ui.console.Console;
import eu.oschl.ui.console.ConsoleColor;

public class Enter implements Command {
    private final Game game;

    public Enter(Game game) {
        this.game = game;
    }

    @Override
    public String[] getTriggers() {
        return new String[]{
                "enter",
                "go",
                "move",
                "walk",
                "through",
                "e"
        };
    }

    @Override
    public String getDescription() {
        return "walk through a passage";
    }

    @Override
    public void execute(String[] args) {
        String input = String.join(" ", args);

        var passages = this.game.getCurrentRoom().getPassages();
        Passage passage = null;

        for (Passage p : passages) {
            if (p.getName().equalsIgnoreCase(input)) {
                passage = p;
                break;
            }
        }

        if (passage == null) {
            for (Passage p : passages) {
                if (p.getOtherRoom(game.getCurrentRoom()).getName().equalsIgnoreCase(input)) {
                    passage = p;
                    break;
                }
            }
        }

        if (passage == null) {
            Console.print("Schmorn doesn't see this passage.", ConsoleColor.RED);
            return;
        }

        var result = passage.passThrough();

        if (!result) {
            Console.print("The passage is blocked, Schmorn can't pass through.", ConsoleColor.RED);
            return;
        }

        printSuccessfulPassage();
    }

    private void printSuccessfulPassage() {
        Console.print("Schmorn crawled through the ");
        Console.print(game.getLastPassage().get().getName(), ConsoleColor.YELLOW);
        Console.print(" and entered ");
        Console.print(game.getCurrentRoom().getName(), ConsoleColor.BLUE);
        Console.print(".");
    }
}

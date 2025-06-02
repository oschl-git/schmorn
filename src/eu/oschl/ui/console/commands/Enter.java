package eu.oschl.ui.console.commands;

import eu.oschl.textadventure.Game;
import eu.oschl.textadventure.exceptions.InvalidGameState;
import eu.oschl.textadventure.map.Passage;
import eu.oschl.ui.console.Console;
import eu.oschl.ui.console.ConsoleColor;

/**
 * This command allows the player to enter a passage in the game.
 *
 * @author Ondřej Schlaichert
 */
public class Enter implements Command {
    private final Game game;

    public Enter(Game game) {
        this.game = game;
    }

    @Override
    public String[] getTriggers() {
        return new String[]{
                "enter",
                "go through",
                "go to",
                "move through",
                "move to",
                "move",
                "walk through",
                "walk to",
                "walk",
                "pass through",
                "pass to",
                "pass",
                "through",
                "open",
        };
    }

    @Override
    public String getDescription() {
        return "walk through a passage";
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Console.print("Where?", ConsoleColor.RED);
            return;
        }

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
            Console.print("This passage does not exist.", ConsoleColor.RED);
            return;
        }

        var printEnterText = !passage.getOtherRoom(game.getCurrentRoom()).wasEntered();

        var result = passage.passThrough(false);

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

        printSuccessfulPassage(printEnterText);
    }

    /**
     * Prints a message indicating that the player has successfully passed through a passage.
     *
     * @param printEnterText whether to print the enter text of the current room
     */
    private void printSuccessfulPassage(boolean printEnterText) {
        if (game.getLastPassage().isEmpty()) {
            throw new InvalidGameState("Last passages stack is empty even though a passage was passed");
        }

        Console.print("Passed through the ");
        Console.print(game.getLastPassage().get().getName(), ConsoleColor.YELLOW);
        Console.print(" and entered ");
        Console.print(game.getCurrentRoom().getName(), ConsoleColor.BLUE);
        Console.print(".");

        if (game.getCurrentRoom().isBlockedByEnemy()) {
            Console.printLine();
            Console.print("...", ConsoleColor.WHITE);
            Console.printLine();
            Console.print("There is somebody in here.", ConsoleColor.WHITE);
        }

        if (printEnterText && game.getCurrentRoom().getEnterText().isPresent()) {
            Console.printLine();
            Console.printLine();
            Console.print(game.getCurrentRoom().getEnterText().get());
            Console.printLine();
        }
    }
}

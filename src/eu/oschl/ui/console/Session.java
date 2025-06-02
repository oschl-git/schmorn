package eu.oschl.ui.console;

import eu.oschl.textadventure.Game;
import eu.oschl.ui.console.exceptions.InvalidInput;

import java.util.Random;

/**
 * This class represents a game session in the console application.
 * It processes user commands and handles the game loop.
 *
 * @author Ondřej Schlaichert
 */
public class Session {
    private final Game game;
    private final CommandProcessor commandProcessor;
    private final Random random;

    public Session(Game game) {
        this.game = game;
        this.commandProcessor = CommandProcessor.create(game);
        this.random = new Random();
    }

    /**
     * Starts the game session, displaying the prologue and entering the main game loop.
     * The loop continues until the game is no longer running, after which it displays the epilogue and the game ends.
     */
    public void start() {
        Console.print(game.getPrologue());
        Console.printLine();

        while (game.isRunning()) {
            var input = Console.readString(">");

            if (input.isEmpty()) {
                continue;
            }

            try {
                this.commandProcessor.processInput(input);
            } catch (InvalidInput e) {
                var message = game.getUnknownCommandMessages()[random.nextInt(game.getUnknownCommandMessages().length)];
                Console.print(message, ConsoleColor.RED);
            }

            Console.printLine();
        }

        Console.printLine();
        Console.print(game.getEpilogue());
    }
}

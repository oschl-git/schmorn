package eu.oschl.ui.console;

import eu.oschl.textadventure.Game;
import eu.oschl.ui.console.exceptions.InvalidInput;

import java.util.Random;

public class Session {
    private final Game game;
    private final CommandProcessor commandProcessor;
    private final Random random;

    public Session(Game game) {
        this.game = game;
        this.commandProcessor = CommandProcessor.create(game);
        this.random = new Random();
    }

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

package eu.oschl.ui.console.commands;

import eu.oschl.textadventure.Game;
import eu.oschl.textadventure.map.Passage;
import eu.oschl.ui.console.Console;
import eu.oschl.ui.console.ConsoleColor;

public class Explore implements Command {
    private final Game game;

    public Explore(Game game) {
        this.game = game;
    }

    @Override
    public String[] getTriggers() {
        return new String[]{
                "explore around",
                "explore",
                "look around",
                "look",
                "examine around",
                "examine",
        };
    }

    @Override
    public String getDescription() {
        return "find out information about the current area";
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            Console.print("This doesn't make any sense.", ConsoleColor.RED);
            return;
        }

        var room = this.game.getCurrentRoom();
        var objects = room.getObjects();
        var passages = room.getPassages();

        Console.print(room.getName(), ConsoleColor.BG_BLUE);
        Console.printLine();
        Console.print(room.getDescription(), ConsoleColor.BLUE);

        if (room.getEnemy().isPresent()) {
            Console.printLine();
            Console.printLine();

            Console.print("In the middle of the room stands ", ConsoleColor.RED);
            Console.print(room.getEnemy().get().getName(), ConsoleColor.BG_RED);
            Console.print(".", ConsoleColor.RED);
            Console.printLine();
            Console.print(room.getEnemy().get().getDescription(), ConsoleColor.RED);
            Console.printLine();
            Console.printLine();
            Console.print("You must defeat " + room.getEnemy().get().getName() + " to continue further, or turn back.");

            return;
        }

        if (!objects.isEmpty()) {
            Console.printLine();
            Console.printLine();

            if (objects.size() == 1) {
                Console.print("there is something in this room: ", ConsoleColor.MAGENTA);
            } else {
                Console.print("there are " + objects.size() + " things in this room: ", ConsoleColor.MAGENTA);
            }
            for (var object : objects) {
                Console.printLine();
                Console.print(" * " + object.getName(), ConsoleColor.MAGENTA);
                Console.print(", " + object.getDescription());
            }
        }

        if (!passages.isEmpty()) {
            Console.printLine();
            Console.printLine();

            if (passages.size() == 1) {
                Console.print("there is a passage leading out of here: ", ConsoleColor.YELLOW);
            } else {
                Console.print("there are " + passages.size() + " passages leading out of here: ", ConsoleColor.YELLOW);
            }

            for (Passage passage : passages) {
                Console.printLine();
                Console.print(" * " + passage.getName(), ConsoleColor.YELLOW);

                if (passage.getDescription().isPresent()) {
                    Console.print(", " + passage.getDescription().get());
                }

                if (passage.isSeeThrough()) {
                    Console.print(" - leading to ");
                    Console.print(passage.getOtherRoom(room).getName(), ConsoleColor.BLUE);
                } else {
                    Console.print(" - it's impossible to see what's on the other side", ConsoleColor.RED);
                }
            }
        }
    }
}

package eu.oschl.schmorn;

import eu.oschl.ui.console.Session;

/**
 * This class serves as the entry point for the game.
 *
 * @author Ondřej Schlaichert
 */
class Main {
    public static void main(String[] args) {
        var game = Setup.createGame();
        var session = new Session(game);

        session.start();
    }
}
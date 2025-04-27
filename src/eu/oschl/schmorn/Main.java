package eu.oschl.schmorn;

import eu.oschl.ui.console.Session;

class Main {
    public static void main(String[] args) {
        var game = Setup.createGame();
        var session = new Session(game);

        session.start();
    }
}
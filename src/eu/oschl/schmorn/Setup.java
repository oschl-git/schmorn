package eu.oschl.schmorn;

import eu.oschl.textadventure.Game;

class Setup {
    public static Game createGame() {
        var prologue = """
                You wake up in a dark room. You don't know how you got here.
                """;

        var epilogue = """
                Everyone is dead. You are the last one left.
                """;

        var unknownCommandMessages = new String[]{
                "Schmorn doesn't know how to do that.",
                "Schmorn does not understand what that means.",
                "Schmorn is confused by what you mean.",
        };

        return new Game(
                prologue,
                epilogue,
                unknownCommandMessages
        );
    }
}

package eu.oschl.ui.console.commands;

import eu.oschl.textadventure.Game;

public class Quit implements Command {
    private final Game game;

    public Quit(Game game) {
        this.game = game;
    }

    @Override
    public String[] getTriggers() {
        return new String[]{
                "quit",
                "stop"
        };
    }

    @Override
    public String getDescription() {
        return "leave the game and stop playing";
    }

    @Override
    public void execute(String[] args) {
        game.stop();
    }
}

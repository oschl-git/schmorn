package eu.oschl.ui.console.commands;

import eu.oschl.textadventure.Game;

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
        // @TODO: Implement passing between rooms
    }
}

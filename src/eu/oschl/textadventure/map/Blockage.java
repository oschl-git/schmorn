package eu.oschl.textadventure.map;

import eu.oschl.textadventure.Game;

public class Blockage {
    private Game game;

    private final String description;
    private int requiredInteractionsToPass;

    public Blockage(String description, int requiredInteractionsToPass) {
        this.description = description;
        this.requiredInteractionsToPass = requiredInteractionsToPass;
    }

    public void setGame(Game game) {
        if (game == null) {
            return;
        }

        this.game = game;
    }

    public String getDescription() {
        return description;
    }

    public void interact() {
        this.requiredInteractionsToPass--;
    }

    public boolean canPass() {
        return requiredInteractionsToPass <= 0;
    }
}

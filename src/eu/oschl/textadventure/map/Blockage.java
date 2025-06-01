package eu.oschl.textadventure.map;

import eu.oschl.textadventure.Game;

/**
 * Represents a blockage in the game that requires a certain number of interactions to pass.
 * This could be a door, a puzzle, or any other obstacle that the player must overcome.
 *
 * @author Ondřej Schlaichert
 */
public class Blockage {
    private Game game;

    private final String description;
    private int requiredInteractionsToPass;

    public Blockage(String description, int requiredInteractionsToPass) {
        this.description = description;
        this.requiredInteractionsToPass = requiredInteractionsToPass;
    }

    /**
     * Sets the game instance. This is typically called during the game's setup phase.
     *
     * @param game the game instance to associate
     */
    public void setGame(Game game) {
        if (game == null) {
            return;
        }

        this.game = game;
    }

    public String getDescription() {
        return description;
    }

    public int getRequiredInteractionsToPass() {
        return requiredInteractionsToPass;
    }

    /**
     * Decreases the number of required interactions to pass this blockage.
     */
    public void interact() {
        this.requiredInteractionsToPass--;
    }

    /**
     * Checks if the player can pass this blockage.
     *
     * @return true if the player can pass, false otherwise
     */
    public boolean canPass() {
        return requiredInteractionsToPass <= 0;
    }
}

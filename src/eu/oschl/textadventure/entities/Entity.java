package eu.oschl.textadventure.entities;

import eu.oschl.textadventure.Game;

/**
 * Represents a generic entity in the game.
 * This class serves as a base for all entities, such as enemies.
 *
 * @author Ondřej Schlaichert
 */
public abstract class Entity {
    protected Game game;

    protected final String name;
    protected final String description;

    public Entity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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
}

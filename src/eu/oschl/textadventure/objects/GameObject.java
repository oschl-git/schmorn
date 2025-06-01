package eu.oschl.textadventure.objects;

import eu.oschl.textadventure.Game;

/**
 * Represents a generic game object that can be part of the game world.
 * This class serves as a base for all game objects, providing common properties and methods.
 *
 * @author Ondřej Schlaichert
 */
public abstract class GameObject {
    protected Game game;

    protected final String name;
    protected final String description;

    public GameObject(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

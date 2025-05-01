package eu.oschl.textadventure.entities;

import eu.oschl.textadventure.Game;

public abstract class Entity {
    protected Game game;

    protected final String name;
    protected final String description;

    public Entity(String name, String description) {
        this.name = name;
        this.description = description;
    }

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

package eu.oschl.textadventure.objects;

import eu.oschl.textadventure.Blockage;
import eu.oschl.textadventure.Game;

public abstract class GameObject {
    protected Game game;
    protected final String name;
    protected final String description;
    protected final Blockage unblocks;

    public GameObject(String name, String description, Blockage unblocks) {
        this.name = name;
        this.description = description;
        this.unblocks = unblocks;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public abstract boolean use();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

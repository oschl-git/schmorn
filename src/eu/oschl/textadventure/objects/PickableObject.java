package eu.oschl.textadventure.objects;

import eu.oschl.textadventure.Blockage;
import eu.oschl.textadventure.Room;

public class PickableObject extends GameObject {
    private final Room mustBeUsedIn;

    public PickableObject(String name, String description, Blockage unblocks, Room mustBeUsedIn) {
        super(name, description, unblocks);
        this.mustBeUsedIn = mustBeUsedIn;
    }

    public void pickUp() {
        game.getInventory().addItem(this);
        game.getCurrentRoom().removeObject(this);
    }

    @Override
    public boolean use() {
        if (mustBeUsedIn != null && mustBeUsedIn == game.getCurrentRoom()) {
            unblocks.unblock();
            return true;
        }

        return false;
    }
}

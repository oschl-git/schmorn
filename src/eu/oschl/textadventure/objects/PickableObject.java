package eu.oschl.textadventure.objects;

import eu.oschl.textadventure.map.Blockage;
import eu.oschl.textadventure.map.Room;

import java.util.Arrays;

public class PickableObject extends GameObject {
    private final Room[] canBeUsedIn;
    private final Blockage interactsWith;

    public PickableObject(String name, String description, Room[] canBeUsedIn, Blockage interactsWith) {
        super(name, description);
        this.canBeUsedIn = canBeUsedIn;
        this.interactsWith = interactsWith;
    }

    public boolean pickUp() {
        return game.getInventory().addItem(this);
    }

    public boolean use() {
        if (!Arrays.asList(canBeUsedIn).contains(game.getCurrentRoom())) {
            return false;
        }

        interactsWith.interact();
        game.getInventory().removeItem(this);

        return true;
    }
}

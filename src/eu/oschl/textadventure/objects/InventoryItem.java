package eu.oschl.textadventure.objects;

import eu.oschl.textadventure.map.Blockage;
import eu.oschl.textadventure.map.Room;

import java.util.Arrays;

public class InventoryItem extends PickableObject {
    private final String useText;
    private final Room[] canBeUsedIn;
    private final Blockage interactsWith;

    public InventoryItem(String name, String description, String useText, Room[] canBeUsedIn, Blockage interactsWith) {
        super(name, description);
        this.useText = useText;
        this.canBeUsedIn = canBeUsedIn;
        this.interactsWith = interactsWith;
    }

    public String getUseText() {
        return useText;
    }

    public boolean pickUp() {
        var result = game.getInventory().addItem(this);

        if (result) {
            game.getCurrentRoom().removeObject(this);
        }

        return result;
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

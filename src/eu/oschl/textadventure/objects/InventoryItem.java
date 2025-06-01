package eu.oschl.textadventure.objects;

import eu.oschl.textadventure.map.Blockage;
import eu.oschl.textadventure.map.Room;

import java.util.Arrays;

/**
 * Represents an item in the player's inventory that can be picked up and used in specific rooms.
 *
 * @author Ondřej Schlaichert
 */
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

    public Room[] getCanBeUsedIn() {
        return canBeUsedIn;
    }

    public Blockage getInteractsWith() {
        return interactsWith;
    }

    /**
     * Attempts to pick up the item. If the current room is blocked by an enemy, the item cannot be picked up.
     *
     * @return true if the item was successfully picked up, false otherwise
     */
    public boolean pickUp() {
        if (game.getCurrentRoom().isBlockedByEnemy()) {
            return false;
        }

        var result = game.getInventory().addItem(this);

        if (result) {
            game.getCurrentRoom().removeObject(this);
        }

        return result;
    }

    /**
     * Attempts to use the item in the current room.
     *
     * @return true if the item was successfully used, false if it cannot be used in the current room
     */
    public boolean use() {
        if (!Arrays.asList(canBeUsedIn).contains(game.getCurrentRoom())) {
            return false;
        }

        interactsWith.interact();
        game.getInventory().removeItem(this);

        return true;
    }
}

package eu.oschl.textadventure.objects;

/**
 * Represents an object in the game that can be picked up by the player.
 * This class serves as a base for all pickable objects, providing common properties and methods.
 *
 * @author Ondřej Schlaichert
 */
public abstract class PickableObject extends GameObject {
    public PickableObject(String name, String description) {
        super(name, description);
    }

    /**
     * Picks up the object and adds it to the player's inventory.
     * @return true if the object was successfully picked up, false otherwise
     */
    public abstract boolean pickUp();
}

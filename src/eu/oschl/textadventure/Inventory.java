package eu.oschl.textadventure;

import eu.oschl.textadventure.objects.InventoryItem;
import eu.oschl.textadventure.objects.Weapon;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Represents the player's inventory in the game, which can hold a limited number of items and a weapon.
 *
 * @author Ondřej Schlaichert
 */
public class Inventory {
    private static final int MAX_ITEMS = 5;

    public final ArrayList<InventoryItem> items;
    public Weapon weapon;

    public Inventory() {
        this.items = new ArrayList<>();
        this.weapon = null;
    }

    public ArrayList<InventoryItem> getItems() {
        return items;
    }

    public Optional<Weapon> getWeapon() {
        return Optional.ofNullable(weapon);
    }

    /**
     * Attempts to add an item to the inventory. If the inventory is full, the item cannot be added.
     *
     * @param item the item to add
     * @return true if the item was successfully added, false if the inventory is full
     */
    public boolean addItem(InventoryItem item) {
        if (items.size() >= MAX_ITEMS) {
            return false;
        }

        items.add(item);
        return true;
    }

    /**
     * Attempts to remove an item from the inventory.
     *
     * @param item the item to remove
     * @return true if the item was successfully removed, false if the item was not found in the inventory
     */
    public boolean removeItem(InventoryItem item) {
        if (!items.contains(item)) {
            return false;
        }

        items.remove(item);
        return true;
    }

    /**
     * Sets the weapon in the inventory. If a weapon is already set, it will be replaced.
     *
     * @param weapon the weapon to set
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
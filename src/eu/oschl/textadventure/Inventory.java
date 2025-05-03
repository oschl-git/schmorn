package eu.oschl.textadventure;

import eu.oschl.textadventure.objects.InventoryItem;
import eu.oschl.textadventure.objects.Weapon;

import java.util.ArrayList;
import java.util.Optional;

public class Inventory {
    private static final int MAX_ITEMS = 5;

    public final ArrayList<InventoryItem> items;
    public Weapon weapon;

    public Inventory() {
        this.items = new ArrayList<>();
        this.weapon = null;
    }

    public boolean addItem(InventoryItem item) {
        if (items.size() >= MAX_ITEMS) {
            return false;
        }

        items.add(item);
        return true;
    }

    public boolean removeItem(InventoryItem item) {
        if (!items.contains(item)) {
            return false;
        }

        items.remove(item);
        return true;
    }

    public ArrayList<InventoryItem> getItems() {
        return items;
    }

    public Optional<Weapon> getWeapon() {
        return Optional.ofNullable(weapon);
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
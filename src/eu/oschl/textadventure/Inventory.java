package eu.oschl.textadventure;

import eu.oschl.textadventure.objects.PickableObject;
import eu.oschl.textadventure.objects.Weapon;

import java.util.ArrayList;

public class Inventory {
    private final Game game;

    public final ArrayList<PickableObject> items;
    public final ArrayList<Weapon> weapons;

    public Inventory(Game game) {
        this.game = game;
        this.items = new ArrayList<>();
        this.weapons = new ArrayList<>();
    }

    public void addItem(PickableObject item) {
        items.add(item);
    }

    public void removeItem(PickableObject item) {
        items.remove(item);
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void removeWeapon(Weapon weapon) {
        weapons.remove(weapon);
    }

    public ArrayList<PickableObject> getItems() {
        return items;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }
}